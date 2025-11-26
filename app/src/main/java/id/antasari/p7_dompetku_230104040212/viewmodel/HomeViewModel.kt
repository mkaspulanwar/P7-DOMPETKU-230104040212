package id.antasari.p7_dompetku_230104040212.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import id.antasari.p7_dompetku_230104040212.data.local.AssetPreferences
import id.antasari.p7_dompetku_230104040212.model.Asset
import java.text.NumberFormat
import java.util.Locale

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val assetPrefs = AssetPreferences(application)
    
    // 1. Init _assets dengan data dari storage, bukan dummy
    private val _assets = mutableStateListOf<Asset>().apply {
        addAll(assetPrefs.getAssets())
    }
    
    val assets: List<Asset> get() = _assets

    private fun formatRupiah(amount: Double): String {
        val currencyFormat = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        currencyFormat.minimumFractionDigits = 2
        currencyFormat.maximumFractionDigits = 2
        val formatted = currencyFormat.format(amount)
        return formatted.replace("Rp ", "Rp") 
    }

    val totalPortfolioValue: String
        get() {
            val total = _assets.sumOf { asset ->
                val cleanString = asset.marketValue.replace("[^\\d,]".toRegex(), "")
                val doubleString = cleanString.replace(",", ".")
                doubleString.toDoubleOrNull() ?: 0.0
            }
            return formatRupiah(total)
        }

    fun addAsset(
        ticker: String, 
        name: String, 
        marketValueRaw: String, 
        quantityRaw: String, 
        category: String, 
        iconResId: Int
    ) {
        val marketValueDouble = marketValueRaw.replace(",", ".").toDoubleOrNull() ?: 0.0
        val formattedMarketValue = formatRupiah(marketValueDouble)

        val formattedQuantity = if (quantityRaw.all { it.isDigit() || it == '.' || it == ',' }) {
            when (category) {
                "Crypto" -> "$quantityRaw $ticker"
                "Saham" -> "$quantityRaw Lembar"
                "Kas" -> formatRupiah(quantityRaw.replace(",", ".").toDoubleOrNull() ?: 0.0)
                else -> "$quantityRaw Unit"
            }
        } else {
            quantityRaw
        }

        val newAsset = Asset(
            ticker = ticker,
            name = name,
            marketValue = formattedMarketValue,
            quantity = formattedQuantity,
            category = category,
            iconResId = iconResId
        )
        
        _assets.add(0, newAsset)
        
        // 2. Simpan perubahan ke storage setiap kali add
        saveAssetsToStorage()
    }

    fun removeLastAsset() {
        if (_assets.isNotEmpty()) {
            _assets.removeLast()
            // 3. Simpan perubahan ke storage setiap kali hapus
            saveAssetsToStorage()
        }
    }

    // Fungsi helper untuk menyimpan list saat ini ke Prefs
    private fun saveAssetsToStorage() {
        // Kita perlu mengirimkan List biasa (bukan MutableStateList) ke helper
        assetPrefs.saveAssets(_assets.toList())
    }
}

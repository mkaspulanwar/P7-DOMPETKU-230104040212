package id.antasari.p7_dompetku_230104040212.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import id.antasari.p7_dompetku_230104040212.model.Asset
import id.antasari.p7_dompetku_230104040212.model.dummyAssets
import java.text.NumberFormat
import java.util.Locale

class HomeViewModel : ViewModel() {
    private val _assets = mutableStateListOf<Asset>().apply { addAll(dummyAssets) }
    val assets: List<Asset> get() = _assets

    // Helper untuk format Rupiah sesuai PUEBI (Rp10.000,00)
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

    // Fungsi tambah asset yang diperbarui dengan parameter kategori
    fun addAsset(
        ticker: String, 
        name: String, 
        marketValueRaw: String, 
        quantityRaw: String, 
        category: String, // Parameter baru
        iconResId: Int
    ) {
        // 1. Format Market Value (Rp)
        val marketValueDouble = marketValueRaw.replace(",", ".").toDoubleOrNull() ?: 0.0
        val formattedMarketValue = formatRupiah(marketValueDouble)

        // 2. Format Quantity berdasarkan Kategori
        val formattedQuantity = if (quantityRaw.all { it.isDigit() || it == '.' || it == ',' }) {
            when (category) {
                "Crypto" -> "$quantityRaw $ticker"  // Contoh: "0.05 BTC"
                "Saham" -> "$quantityRaw Lembar"   // Contoh: "100 Lembar"
                "Kas" -> formatRupiah(quantityRaw.replace(",", ".").toDoubleOrNull() ?: 0.0) // Contoh: "Rp1.000.000,00"
                else -> "$quantityRaw Unit"
            }
        } else {
            quantityRaw // Jika user sudah mengetik manual lengkap
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
    }

    fun removeLastAsset() {
        if (_assets.isNotEmpty()) {
            _assets.removeLast()
        }
    }
}

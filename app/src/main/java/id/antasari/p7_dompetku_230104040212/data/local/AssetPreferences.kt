package id.antasari.p7_dompetku_230104040212.data.local

import android.content.Context
import android.content.SharedPreferences
import id.antasari.p7_dompetku_230104040212.model.Asset
import org.json.JSONArray
import org.json.JSONObject

/**
 * Helper untuk menyimpan daftar Asset ke SharedPreferences.
 * Menggunakan serialisasi JSON manual sederhana.
 */
class AssetPreferences(context: Context) {
    private val PREFS_NAME = "dompetku_asset_prefs"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val KEY_ASSETS = "assets_data"

    fun saveAssets(assets: List<Asset>) {
        val jsonArray = JSONArray()
        assets.forEach { asset ->
            val jsonObject = JSONObject().apply {
                put("ticker", asset.ticker)
                put("name", asset.name)
                put("marketValue", asset.marketValue)
                put("quantity", asset.quantity)
                put("category", asset.category)
                put("iconResId", asset.iconResId)
                // iconTint kita abaikan (null) karena sulit diserialisasi dan defaultnya null
            }
            jsonArray.put(jsonObject)
        }
        prefs.edit().putString(KEY_ASSETS, jsonArray.toString()).apply()
    }

    fun getAssets(): List<Asset> {
        val jsonString = prefs.getString(KEY_ASSETS, null)
        if (jsonString.isNullOrEmpty()) return emptyList()

        val assets = mutableListOf<Asset>()
        try {
            val jsonArray = JSONArray(jsonString)
            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                assets.add(
                    Asset(
                        ticker = obj.getString("ticker"),
                        name = obj.getString("name"),
                        marketValue = obj.getString("marketValue"),
                        quantity = obj.getString("quantity"),
                        category = obj.optString("category", "Crypto"), // Default jika data lama tdk punya kategori
                        iconResId = obj.getInt("iconResId"),
                        iconTint = null
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return assets
    }
    
    // Opsi: Function untuk menghapus data jika diperlukan reset total
    fun clearAssets() {
        prefs.edit().remove(KEY_ASSETS).apply()
    }
}

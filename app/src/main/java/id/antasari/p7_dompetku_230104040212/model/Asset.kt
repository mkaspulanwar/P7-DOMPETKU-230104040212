package id.antasari.p7_dompetku_230104040212.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import id.antasari.p7_dompetku_230104040212.R

data class Asset(
    val ticker: String,
    val name: String,
    val marketValue: String,
    val quantity: String,
    val category: String, // Field Baru: Kategori Asset (Crypto, Saham, Kas)
    @DrawableRes val iconResId: Int,
    val iconTint: Color? = null
)

// Initial Dummy Data (PUEBI Format: Rp123.456,00 tanpa spasi)
val dummyAssets = listOf(
    Asset("BTC", "Bitcoin", "Rp1.450.000,00", "0.0007 BTC", "Crypto", R.drawable.bitcoin_logo, null),
    Asset("BBCA", "Bank Central Asia Tbk", "Rp925.000,00", "100 Lembar", "Saham", R.drawable.bbca_logo, null),
    Asset("CDIA", "Chandra Daya Investasi Tbk", "Rp750.000,00", "400 Lembar", "Saham", R.drawable.cdia_logo, null),
    Asset("SIMP", "Salim Ivomas Pratama Tbk", "Rp675.000,00", "1000 Lembar", "Saham", R.drawable.simp_logo, null),
    Asset("IDR", "Uang Kas", "Rp500.000,00", "Rp500.000", "Kas", R.drawable.rupiah_logo, null),
    Asset("NVDA", "Nvidia Inc", "Rp130.000,00", "0.04 Lembar", "Saham", R.drawable.nvidia_logo, null),
)

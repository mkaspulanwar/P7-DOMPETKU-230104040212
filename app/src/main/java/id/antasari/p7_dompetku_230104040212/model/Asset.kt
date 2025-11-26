package id.antasari.p7_dompetku_230104040212.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import id.antasari.p7_dompetku_230104040212.R

data class Asset(
    val ticker: String,
    val name: String,
    val marketValue: String,
    val quantity: String,
    val category: String,
    @DrawableRes val iconResId: Int,
    val iconTint: Color? = null
)

// Kosongkan data dummy agar user baru mulai dari nol
val dummyAssets = emptyList<Asset>()

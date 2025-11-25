package id.antasari.p7_dompetku_230104040212.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// Sudut membulat khas MD3
val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp), // Untuk Button, TextField [cite: 85]
    large = RoundedCornerShape(16.dp),  // Untuk Card besar seperti Spot Balance
    extraLarge = RoundedCornerShape(24.dp)
)
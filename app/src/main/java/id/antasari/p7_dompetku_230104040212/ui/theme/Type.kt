package id.antasari.p7_dompetku_230104040212.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Anda dapat menggunakan Font default atau menambahkan custom font
// Contoh menggunakan Font default Android:
val AppFontFamily = FontFamily.Default

// Set of Material typography styles to start with
val Typography = Typography(
    // 1. Headline (untuk judul besar di halaman)
    headlineLarge = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),

    // 2. Title (untuk judul di TopBar atau Card)
    titleLarge = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    // 3. Body (untuk konten teks utama)
    bodyLarge = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    // 4. Label (untuk label tombol atau keterangan kecil)
    labelMedium = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )

    // ... Anda bisa menambahkan style lain (Display, Headline, Title, Body, Label) [cite: 74, 75, 76, 77, 78]
)

/* Catatan: Pastikan Anda telah mengintegrasikan "Typography" ini ke dalam
   MaterialTheme di file "Theme.kt" Anda:
   MaterialTheme(
       // ...
       typography = Typography, // <-- Ini harus di-import
       // ...
   )
*/
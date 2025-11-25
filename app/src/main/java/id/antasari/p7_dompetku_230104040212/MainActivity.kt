package id.antasari.p7_dompetku_230104040212

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import id.antasari.p7_dompetku_230104040212.navigation.AppNavHost
import id.antasari.p7_dompetku_230104040212.ui.theme.DompetkuTheme // Sesuaikan import ini!

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // STATE INI SANGAT PENTING UNTUK MENGONTROL DARK MODE
            var useDarkTheme by remember { mutableStateOf(false) }

            DompetkuTheme(
                darkTheme = useDarkTheme // Menggunakan state dari sini
            ) {
                AppNavHost(
                    toggleTheme = { useDarkTheme = !useDarkTheme }, // Fungsi untuk mengubah state
                    isDarkTheme = useDarkTheme
                )
            }
        }
    }
}
// Catatan: Anda perlu sedikit memodifikasi AppNavHost.kt agar menerima parameter toggleTheme
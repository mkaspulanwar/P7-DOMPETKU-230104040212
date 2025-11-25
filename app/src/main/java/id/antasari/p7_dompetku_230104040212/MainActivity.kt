package id.antasari.p7_dompetku_230104040212

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import id.antasari.p7_dompetku_230104040212.navigation.AppNavHost
import id.antasari.p7_dompetku_230104040212.ui.theme.DompetkuTheme
import androidx.core.view.WindowCompat // <--- PASTIKAN IMPORT INI ADA

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        // *** FIX 1: PENTING UNTUK MENGHILANGKAN FLICKER ***
        // Mengaktifkan drawing edge-to-edge sebelum memanggil super.onCreate
        WindowCompat.setDecorFitsSystemWindows(window, false)

        super.onCreate(savedInstanceState)
        setContent {
            var useDarkTheme by remember { mutableStateOf(false) }

            DompetkuTheme(
                darkTheme = useDarkTheme
            ) {
                AppNavHost(
                    toggleTheme = { useDarkTheme = !useDarkTheme },
                    isDarkTheme = useDarkTheme
                )
            }
        }
    }
}
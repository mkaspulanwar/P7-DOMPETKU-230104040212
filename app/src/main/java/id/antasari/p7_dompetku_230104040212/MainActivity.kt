package id.antasari.p7_dompetku_230104040212

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.core.view.WindowCompat
import id.antasari.p7_dompetku_230104040212.data.local.UserPreferences
import id.antasari.p7_dompetku_230104040212.navigation.AppNavHost
import id.antasari.p7_dompetku_230104040212.navigation.Destinations
import id.antasari.p7_dompetku_230104040212.ui.theme.DompetkuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        // *** FIX 1: PENTING UNTUK MENGHILANGKAN FLICKER ***
        WindowCompat.setDecorFitsSystemWindows(window, false)

        super.onCreate(savedInstanceState)

        // *** FIX 2: Cek Status Login SEBELUM setContent ***
        val userPrefs = UserPreferences(applicationContext)
        val startDestination = if (userPrefs.isLoggedIn()) {
            Destinations.HOME_ROUTE
        } else {
            Destinations.LOGIN_ROUTE
        }

        setContent {
            var useDarkTheme by remember { mutableStateOf(false) }

            DompetkuTheme(
                darkTheme = useDarkTheme
            ) {
                // *** FIX 3: Kirim startDestination dinamis ke AppNavHost ***
                AppNavHost(
                    startDestination = startDestination,
                    toggleTheme = { useDarkTheme = !useDarkTheme },
                    isDarkTheme = useDarkTheme
                )
            }
        }
    }
}

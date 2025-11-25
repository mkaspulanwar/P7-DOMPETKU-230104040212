package id.antasari.p7_dompetku_230104040212.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.antasari.p7_dompetku_230104040212.ui.screens.HomeScreen
import id.antasari.p7_dompetku_230104040212.ui.screens.LoginScreen
import id.antasari.p7_dompetku_230104040212.ui.screens.ProfileScreen
import id.antasari.p7_dompetku_230104040212.ui.screens.SettingsScreen
import androidx.compose.animation.EnterTransition // Import yang diperlukan
import androidx.compose.animation.ExitTransition // Import yang diperlukan
// androidx.compose.animation.core.tween tidak lagi diperlukan, tetapi tidak menyebabkan error

// Definisikan rute (screen) Anda
object Destinations {
    const val LOGIN_ROUTE = "login"
    const val HOME_ROUTE = "home"
    const val PROFILE_ROUTE = "profile"
    const val SETTINGS_ROUTE = "settings"
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.LOGIN_ROUTE,
    toggleTheme: () -> Unit,
    isDarkTheme: Boolean
) {
    // transitionDuration tidak lagi digunakan

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // --- LOGIN ROUTE (Tanpa Transisi) ---
        composable(Destinations.LOGIN_ROUTE) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Destinations.HOME_ROUTE) {
                        popUpTo(Destinations.LOGIN_ROUTE) { inclusive = true }
                    }
                }
            )
        }

        // --- HOME ROUTE (Animasi Dihapus) ---
        composable(
            Destinations.HOME_ROUTE,
            enterTransition = { EnterTransition.None }, // Transisi masuk instan
            exitTransition = { ExitTransition.None }   // Transisi keluar instan
        ) {
            HomeScreen(navController = navController)
        }

        // --- PROFILE ROUTE (Animasi Dihapus) ---
        composable(
            Destinations.PROFILE_ROUTE,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            ProfileScreen(navController = navController)
        }

        // --- SETTINGS ROUTE (Animasi Dihapus) ---
        composable(
            Destinations.SETTINGS_ROUTE,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            SettingsScreen(
                navController = navController,
                toggleTheme = toggleTheme,
                isDarkTheme = isDarkTheme
            )
        }
    }
}
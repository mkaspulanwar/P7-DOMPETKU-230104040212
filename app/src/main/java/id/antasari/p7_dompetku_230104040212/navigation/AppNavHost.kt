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
    startDestination: String = Destinations.LOGIN_ROUTE, // Mulai dari Login
    // Parameter BARU untuk kontrol tema:
    toggleTheme: () -> Unit,
    isDarkTheme: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Destinations.LOGIN_ROUTE) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Destinations.HOME_ROUTE) {
                        // Mencegah kembali ke Login setelah sukses
                        popUpTo(Destinations.LOGIN_ROUTE) { inclusive = true }
                    }
                }
            )
        }

        composable(Destinations.HOME_ROUTE) {
            HomeScreen(navController = navController)
        }

        composable(Destinations.PROFILE_ROUTE) {
            // ProfileScreen tidak membutuhkan toggleTheme, tapi membutuhkan navController
            ProfileScreen(navController = navController)
        }

        composable(Destinations.SETTINGS_ROUTE) {
            // Meneruskan parameter toggleTheme dan isDarkTheme ke SettingsScreen
            SettingsScreen(
                navController = navController,
                toggleTheme = toggleTheme,
                isDarkTheme = isDarkTheme
            )
        }
    }
}
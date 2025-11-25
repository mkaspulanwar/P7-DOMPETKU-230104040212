package id.antasari.p7_dompetku_230104040212.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// 1. Definisikan ColorScheme untuk Light dan Dark
private val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = OnPrimaryLight,
    primaryContainer = PrimaryContainerLight,
    onPrimaryContainer = OnPrimaryContainerLight,
    secondary = SecondaryLight,
    onSecondary = OnSecondaryLight,
    background = BackgroundLight,
    surface = SurfaceLight,
    onBackground = OnBackgroundLight,
    error = ErrorLight
    // ... definisikan semua warna MD3 lainnya (anda mungkin perlu mendefinisikan PrimaryLight, dll. di Color.kt)
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = OnPrimaryDark,
    primaryContainer = PrimaryContainerDark,
    onPrimaryContainer = OnPrimaryContainerDark,
    secondary = SecondaryDark,
    onSecondary = OnSecondaryDark,
    background = BackgroundDark,
    surface = SurfaceDark,
    onBackground = OnBackgroundDark,
    error = ErrorDark
    // ... definisikan semua warna MD3 lainnya
)

// 2. Composable Theme utama
@Composable
fun DompetkuTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // Mengubah warna system bar
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            // Mengatur warna Status Bar
            window.statusBarColor = colorScheme.background.toArgb()

            // *** FIX 3: Mengatur warna Navigation Bar (Bar bawah) agar sesuai background tema ***
            window.navigationBarColor = colorScheme.background.toArgb()

            // Mengatur Appearance (ikon) pada System Bar
            val controller = WindowCompat.getInsetsController(window, view)
            controller.isAppearanceLightStatusBars = !darkTheme

            // *** FIX 4: Mengatur Appearance (ikon) pada Navigation Bar agar sesuai tema ***
            controller.isAppearanceLightNavigationBars = !darkTheme
        }
    }

    // 3. Terapkan Theme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Asumsi ini diimpor dari Type.kt
        shapes = AppShapes,      // Asumsi ini diimpor dari Shape.kt
        content = content
    )
}
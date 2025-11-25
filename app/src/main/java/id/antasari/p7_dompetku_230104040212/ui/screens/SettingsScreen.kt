package id.antasari.p7_dompetku_230104040212.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
// --- IMPORT WAJIB DITAMBAHKAN ---
import androidx.compose.ui.graphics.vector.ImageVector // <-- SOLUSI UNRESOLVED REFERENCE
// --------------------------------
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.antasari.p7_dompetku_230104040212.navigation.Destinations

// Anotasi ini menghilangkan warning 'experimental API' pada SettingsScreen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    toggleTheme: () -> Unit, // Fungsi dari MainActivity untuk Tugas 4
    isDarkTheme: Boolean // State Dark Mode saat ini
) {
    Scaffold(
        topBar = {
            SettingsTopAppBar()
        },
        bottomBar = {
            AppBottomBar(
                navController = navController,
                currentRoute = Destinations.SETTINGS_ROUTE
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(
                text = "Tampilan",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
            )

            // Item Setting Light/Dark Mode (Tugas 4)
            SettingsToggleItem(
                icon = if (isDarkTheme) Icons.Default.ModeNight else Icons.Default.LightMode,
                title = "Mode Gelap (Dark Mode)",
                subtitle = if (isDarkTheme) "Aktif" else "Nonaktif",
                checked = isDarkTheme,
                onCheckedChange = { toggleTheme() }
            )
            Divider()

            // Item Setting Umum
            SettingsItem(
                icon = Icons.Default.Language,
                title = "Bahasa",
                subtitle = "Indonesia (ID)"
            ) { /* Aksi Ganti Bahasa */ }
            Divider()

            SettingsItem(
                icon = Icons.Default.Info,
                title = "Tentang Aplikasi",
                subtitle = "Versi 1.0.0"
            ) { /* Aksi Tentang */ }
            Divider()
        }
    }
}

// TopAppBar Pengaturan
@OptIn(ExperimentalMaterial3Api::class) // <-- SOLUSI WARNING EXPERIMENTAL API
@Composable
fun SettingsTopAppBar() {
    CenterAlignedTopAppBar(
        title = { Text("Pengaturan", style = MaterialTheme.typography.titleLarge) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}

// Item Setting Statis
@Composable
fun SettingsItem(
    icon: ImageVector, // <-- Tipe data sudah benar
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = { Text(title, style = MaterialTheme.typography.bodyLarge) },
        supportingContent = { Text(subtitle, style = MaterialTheme.typography.bodySmall) },
        leadingContent = { Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
        modifier = Modifier.clickable(onClick = onClick)
    )
}

// Item Setting dengan Toggle Switch
@Composable
fun SettingsToggleItem(
    icon: ImageVector, // <-- Tipe data sudah benar
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    ListItem(
        headlineContent = { Text(title, style = MaterialTheme.typography.bodyLarge) },
        supportingContent = { Text(subtitle, style = MaterialTheme.typography.bodySmall) },
        leadingContent = { Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
        trailingContent = {
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedTrackColor = MaterialTheme.colorScheme.primary,
                    uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )
        },
        modifier = Modifier.clickable { onCheckedChange(!checked) }
    )
}
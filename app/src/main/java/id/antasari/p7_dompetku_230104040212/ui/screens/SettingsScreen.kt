package id.antasari.p7_dompetku_230104040212.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.antasari.p7_dompetku_230104040212.navigation.Destinations

// Anotasi ini menghilangkan warning 'experimental API'
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    toggleTheme: () -> Unit,
    isDarkTheme: Boolean
) {
    Scaffold(
        topBar = {
            SettingsTopAppBar()
        },
        bottomBar = {
            // Asumsi AppBottomBar tersedia dan diimpor
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
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {

            // --- 1. GRUP PENGATURAN TAMPILAN ---
            SettingsGroup(title = "Tampilan") {
                SettingsToggleItem(
                    icon = if (isDarkTheme) Icons.Default.DarkMode else Icons.Default.LightMode,
                    title = "Mode Gelap",
                    subtitle = if (isDarkTheme) "Aktif" else "Nonaktif",
                    checked = isDarkTheme,
                    onCheckedChange = { toggleTheme() }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- 2. GRUP PENGATURAN UMUM ---
            SettingsGroup(title = "Umum") {
                // Item Ganti Bahasa
                SettingsItem(
                    icon = Icons.Default.Language,
                    title = "Bahasa",
                    subtitle = "Indonesia (ID)"
                ) { /* Aksi Ganti Bahasa */ }
                Divider(modifier = Modifier.padding(horizontal = 16.dp))

                // Item Tentang Aplikasi
                SettingsItem(
                    icon = Icons.Default.Info,
                    title = "Tentang Aplikasi",
                    subtitle = "Versi 1.0.0"
                ) { /* Aksi Tentang */ }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- 3. GRUP DUKUNGAN & KEAMANAN (PENGGANTI LOGOUT) ---
            SettingsGroup(title = "Dukungan & Keamanan") {
                // Item Keamanan Akun
                SettingsItem(
                    icon = Icons.Default.Security,
                    title = "Keamanan Akun",
                    subtitle = "Kelola kata sandi dan autentikasi"
                ) { /* Aksi Keamanan Akun */ }
                Divider(modifier = Modifier.padding(horizontal = 16.dp))

                // Item Pusat Bantuan
                SettingsItem(
                    icon = Icons.Default.HelpCenter,
                    title = "Pusat Bantuan",
                    subtitle = "Pertanyaan, laporan, dan FAQ"
                ) { /* Aksi Pusat Bantuan */ }
            }
        }
    }
}

// ----------------------------------------------------------------------
// KOMPONEN: SettingsGroup (Tidak Berubah)
// ----------------------------------------------------------------------
@Composable
fun SettingsGroup(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Header Grup
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        // Container untuk Grup (menggunakan Surface untuk efek Card/Grouping)
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surfaceContainerLow,
            shadowElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(content = content)
        }
    }
}

// ----------------------------------------------------------------------
// KOMPONEN: SettingsItem (Tidak Berubah, hanya nama parameter textColor dan iconColor)
// ----------------------------------------------------------------------
@Composable
fun SettingsItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    iconColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = {
            Text(title, style = MaterialTheme.typography.bodyLarge, color = textColor)
        },
        supportingContent = { Text(subtitle, style = MaterialTheme.typography.bodySmall) },
        leadingContent = { Icon(icon, contentDescription = null, tint = iconColor) },
        trailingContent = { Icon(Icons.Default.KeyboardArrowRight, contentDescription = null) },
        modifier = Modifier.clickable(onClick = onClick)
    )
}

// ----------------------------------------------------------------------
// KOMPONEN: SettingsToggleItem (Tidak Berubah)
// ----------------------------------------------------------------------
@Composable
fun SettingsToggleItem(
    icon: ImageVector,
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
                onCheckedChange = onCheckedChange
            )
        },
        modifier = Modifier.clickable { onCheckedChange(!checked) }
    )
}

// ----------------------------------------------------------------------
// KOMPONEN: SettingsTopAppBar (Tidak Berubah)
// ----------------------------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsTopAppBar() {
    CenterAlignedTopAppBar(
        title = { Text("Pengaturan", style = MaterialTheme.typography.titleLarge) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}
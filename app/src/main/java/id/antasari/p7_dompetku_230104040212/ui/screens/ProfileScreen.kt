package id.antasari.p7_dompetku_230104040212.ui.screens

import androidx.compose.foundation.Image // Import untuk komponen Image
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
import androidx.compose.ui.layout.ContentScale // Import untuk ContentScale.Crop
import androidx.compose.ui.res.painterResource // Import untuk painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

// Import R untuk mengakses drawable (pastikan package name sesuai)
import id.antasari.p7_dompetku_230104040212.R
import id.antasari.p7_dompetku_230104040212.navigation.Destinations
import id.antasari.p7_dompetku_230104040212.ui.components.AppCard

// Anotasi ini menghilangkan warning 'experimental API' pada CenterAlignedTopAppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        topBar = {
            ProfileTopAppBar(navController)
        },
        bottomBar = {
            AppBottomBar(
                navController = navController,
                currentRoute = Destinations.PROFILE_ROUTE
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // --- BAGIAN REVISI: FOTO PROFIL (profile.png) ---
            Surface(
                shape = MaterialTheme.shapes.extraLarge,
                modifier = Modifier.size(120.dp),
            ) {
                // Menampilkan gambar dari res/drawable/profile.png
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Foto Profil M. Kaspul Anwar",
                    contentScale = ContentScale.Crop, // Memastikan gambar terpotong rapi dalam lingkaran
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            // --- BAGIAN REVISI: NAMA PENGGUNA ---
            Text(
                text = "M. Kaspul Anwar",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(4.dp))

            // --- BAGIAN REVISI: EMAIL ---
            Text(
                text = "mkaspulanwar@gmail.com",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Daftar Aksi/Informasi Profil menggunakan AppCard
            AppCard(modifier = Modifier.fillMaxWidth()) {
                Column {
                    ProfileItem(Icons.Default.Edit, "Edit Profil") { /* Aksi Edit */ }
                    Divider()
                    ProfileItem(Icons.Default.AttachMoney, "Riwayat Transaksi") { /* Aksi Riwayat */ }
                    Divider()
                    // Item Logout
                    ProfileItem(Icons.Default.Logout, "Logout", MaterialTheme.colorScheme.error) {
                        navController.navigate(Destinations.LOGIN_ROUTE) {
                            popUpTo(Destinations.HOME_ROUTE) { inclusive = true }
                        }
                    }
                }
            }
        }
    }
}

// Komponen Reusable untuk item di dalam Card Profil
@Composable
fun ProfileItem(
    icon: ImageVector,
    text: String,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = { Text(text, style = MaterialTheme.typography.bodyLarge) },
        leadingContent = {
            Icon(
                icon,
                contentDescription = text,
                tint = contentColor
            )
        },
        modifier = Modifier
            .heightIn(min = 56.dp)
            .clickable { onClick() }
    )
}

// TopAppBar khusus Profil
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopAppBar(navController: NavController) {
    CenterAlignedTopAppBar(
        title = { Text("Profil Saya", style = MaterialTheme.typography.titleLarge) },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(Destinations.HOME_ROUTE) }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}
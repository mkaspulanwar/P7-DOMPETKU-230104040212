package id.antasari.p7_dompetku_230104040212.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.antasari.p7_dompetku_230104040212.R
import id.antasari.p7_dompetku_230104040212.navigation.Destinations
import id.antasari.p7_dompetku_230104040212.ui.components.AppCard
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.SolidColor // Import untuk SolidColor

// Anotasi ini menghilangkan warning 'experimental API' pada CenterAlignedTopAppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        topBar = {
            ProfileTopAppBar(navController)
        },
        bottomBar = {
            // Asumsi AppBottomBar tersedia (didefinisikan di HomeScreen atau diimpor)
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
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // --- 1. HEADER PROFIL ---
            Spacer(modifier = Modifier.height(16.dp))
            Surface(
                shape = MaterialTheme.shapes.extraLarge,
                modifier = Modifier.size(120.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Foto Profil M. Kaspul Anwar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "M. Kaspul Anwar",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "mkaspulanwar@gmail.com",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- 2. KELOMPOK MENU AKSI (EDIT & RIWAYAT) ---
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 0.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Item 1: Edit Profil
                ModernProfileButton(
                    icon = Icons.Default.Edit,
                    text = "Edit Profil",
                    onClick = { /* Aksi Edit Profil */ }
                )

                // Item 2: Riwayat Transaksi
                ModernProfileButton(
                    icon = Icons.Default.History,
                    text = "Riwayat Transaksi",
                    onClick = { /* Aksi Riwayat Transaksi */ }
                )
            }

            Spacer(modifier = Modifier.weight(1f)) // Mengambil ruang sisa

            // --- 3. TOMBOL LOGOUT TERPISAH DI BAWAH ---
            OutlinedButton(
                onClick = {
                    navController.navigate(Destinations.LOGIN_ROUTE) {
                        popUpTo(Destinations.HOME_ROUTE) { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                ),
                // *** PERBAIKAN: Menggunakan SolidColor untuk Brush ***
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = SolidColor(MaterialTheme.colorScheme.error)
                )
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Logout, contentDescription = "Logout")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Logout", style = MaterialTheme.typography.titleMedium)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// ----------------------------------------------------
// KOMPONEN BARU: ModernProfileButton
// ----------------------------------------------------
@Composable
fun ModernProfileButton(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icon,
                contentDescription = text,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Normal),
                modifier = Modifier.weight(1f)
            )
            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// ----------------------------------------------------
// KOMPONEN LAMA: ProfileTopAppBar & AppBottomBar (Dibiarkan)
// ----------------------------------------------------

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
// Catatan: Pastikan AppBottomBar juga tersedia/diimpor di sini.
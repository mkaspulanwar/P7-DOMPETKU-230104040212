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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import id.antasari.p7_dompetku_230104040212.R
import id.antasari.p7_dompetku_230104040212.navigation.Destinations
import id.antasari.p7_dompetku_230104040212.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel() // Inject ViewModel
) {
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
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // --- 1. HEADER PROFIL ---
            Spacer(modifier = Modifier.height(32.dp))
            Surface(
                shape = MaterialTheme.shapes.extraLarge,
                modifier = Modifier.size(120.dp),
                shadowElevation = 8.dp
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Foto Profil",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "M. Kaspul Anwar",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "mkaspulanwar@gmail.com",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(40.dp))

            // --- 2. KELOMPOK MENU AKSI ---
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ModernProfileButton(
                    icon = Icons.Default.Edit,
                    text = "Edit Profil",
                    onClick = { /* Aksi Edit Profil */ }
                )

                ModernProfileButton(
                    icon = Icons.Default.History,
                    text = "Riwayat Transaksi",
                    onClick = { /* Aksi Riwayat Transaksi */ }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // --- 3. TOMBOL LOGOUT DENGAN LOGIKA ---
            OutlinedButton(
                onClick = {
                    // 1. Hapus sesi login
                    viewModel.logout()
                    
                    // 2. Navigasi ke Login & hapus backstack
                    navController.navigate(Destinations.LOGIN_ROUTE) {
                        popUpTo(0) { inclusive = true } // Hapus semua history
                        launchSingleTop = true
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = SolidColor(MaterialTheme.colorScheme.error)
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Logout, contentDescription = "Logout")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Keluar Aplikasi", style = MaterialTheme.typography.titleMedium)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun ModernProfileButton(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.fillMaxWidth().clickable { onClick() },
        color = MaterialTheme.colorScheme.surfaceContainerLow,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp),
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
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
                modifier = Modifier.weight(1f)
            )
            Icon(
                Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopAppBar(navController: NavController) {
    CenterAlignedTopAppBar(
        title = { Text("Profil Saya", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)) },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(Destinations.HOME_ROUTE) }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

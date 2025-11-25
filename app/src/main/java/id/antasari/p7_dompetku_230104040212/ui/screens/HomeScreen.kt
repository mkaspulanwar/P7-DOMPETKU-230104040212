package id.antasari.p7_dompetku_230104040212.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

// ** Catatan: Ubah package import ini sesuai dengan struktur Anda **
import id.antasari.p7_dompetku_230104040212.navigation.Destinations
import id.antasari.p7_dompetku_230104040212.ui.components.AppCard
import id.antasari.p7_dompetku_230104040212.ui.components.PortfolioItem


// ----------------------------------------------------
// 1. Data Model & Dummy Aset
// ----------------------------------------------------

// Data model direvisi: change24h diganti quantity
data class Asset(
    val name: String,
    val ticker: String,
    val lastPrice: String,
    val quantity: String, // Revisi: Jumlah unit aset
    val icon: ImageVector,
    val iconTint: Color
)

// Data Dummy Portofolio Aset direvisi:
val dummyAssets = listOf(
    Asset("Bitcoin", "BTC", "Rp 980.000.000", "0.012 BTC", Icons.Default.CurrencyBitcoin, Color(0xFFF9A825)),
    Asset("Uang Kas", "IDR", "Rp 5.000.000", "1 Unit", Icons.Default.AttachMoney, Color(0xFF1E88E5)),
    Asset("Bank Central Asia, Tbk", "BBCA", "Rp 9.250", "100 Lembar", Icons.Default.TrendingDown, Color(0xFFD50000)),
)
// ----------------------------------------------------
// 2. Composable Screen Utama
// ----------------------------------------------------

// Anotasi ini menghilangkan warning 'experimental API' pada CenterAlignedTopAppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Destinations.HOME_ROUTE

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "My Portofolio",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    IconButton(onClick = { /* Aksi Notifikasi */ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifikasi")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        bottomBar = {
            AppBottomBar(navController = navController, currentRoute = currentRoute)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 8.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // --- ITEM 1: Card Summary (Total Portofolio) ---
            item {
                AppCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        Text(
                            text = "Total Nilai Portofolio (IDR)", // Revisi Label
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = "Rp 4.250.000", // Revisi Nilai & Mata Uang
                            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.ExtraBold),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(Modifier.height(16.dp))

                        Button(
                            onClick = { /* Aksi Detail */ },
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Text("Security Advices")
                        }
                    }
                }
            }

            // --- ITEM 2: Judul Daftar Aset ---
            item {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Daftar Aset Saya",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // --- ITEM 3: Daftar Aset Portofolio ---
            items(dummyAssets) { asset ->
                PortfolioItem(
                    icon = { Icon(asset.icon, null, tint = asset.iconTint) },
                    name = asset.name,
                    ticker = asset.ticker,
                    lastPrice = asset.lastPrice,
                    quantity = asset.quantity
                )
            }
        }
    }
}

// ----------------------------------------------------
// 3. Bottom Navigation Bar Component
// ----------------------------------------------------

private data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
)

private val bottomNavItems = listOf(
    BottomNavItem(Destinations.HOME_ROUTE, Icons.Default.Home, "Home"),
    BottomNavItem(Destinations.PROFILE_ROUTE, Icons.Default.Person, "Profile"),
    BottomNavItem(Destinations.SETTINGS_ROUTE, Icons.Default.Settings, "Settings"),
)

// Anotasi ini menghilangkan warning 'experimental API' pada NavigationBarItem
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomBar(navController: NavController, currentRoute: String) {
    NavigationBar(
        // Menampilkan sedikit elevasi warna pada surface (M3 standard)
        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
    ) {
        bottomNavItems.forEach { item ->
            val selected = currentRoute == item.route
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = {
                    Text(
                        item.label,
                        style = MaterialTheme.typography.labelMedium
                    )
                },
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
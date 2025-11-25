package id.antasari.p7_dompetku_230104040212.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.res.painterResource
import androidx.annotation.DrawableRes
import id.antasari.p7_dompetku_230104040212.R // Sesuaikan dengan package R proyek Anda!

// ** Catatan: Ubah package import ini sesuai dengan struktur Anda **
import id.antasari.p7_dompetku_230104040212.navigation.Destinations
import id.antasari.p7_dompetku_230104040212.ui.components.AppCard
import id.antasari.p7_dompetku_230104040212.ui.components.PortfolioItem

// ----------------------------------------------------
// 1. Data Model & Dummy Aset
// ----------------------------------------------------

data class Asset(
    val name: String,
    val ticker: String,
    val marketValue: String, // Nama variabel di model data sudah benar: marketValue
    val quantity: String,
    @DrawableRes val iconResId: Int,
    val iconTint: Color? = null
)

val dummyAssets = listOf(
    Asset("Bitcoin", "BTC", "Rp 1.450.000", "0.0007 BTC", R.drawable.bitcoin_logo, null),
    Asset("Uang Kas", "IDR", "Rp 500.000", "1 Unit", R.drawable.rupiah_logo, null),
    Asset("Bank Central Asia, Tbk", "BBCA", "Rp 925.000", "100 Lembar", R.drawable.bbca_logo, null),
    Asset("Nvidia Inc", "NVDA", "Rp 130.000", "0.04 Lembar", R.drawable.nvidia_logo, null),
    Asset("Chandra Daya Investasi Tbk", "CDIA", "Rp 750.000", "400 Lembar", R.drawable.cdia_logo, null),
    Asset("Salim Ivomas Pratama Tbk", "SIMP", "Rp. 675.000", "1000 Lembar", R.drawable.simp_logo, null),
)
// ----------------------------------------------------
// 2. Composable Screen Utama
// ----------------------------------------------------

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
                        text = "M. Kaspul Anwar",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    IconButton(onClick = { /* Aksi Notifikasi */ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifikasi")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
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
                            text = "Total Nilai Portofolio (IDR)",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = "Rp 4.250.000",
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
                    icon = {
                        Image(
                            painter = painterResource(id = asset.iconResId),
                            contentDescription = asset.name,
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    name = asset.name,
                    ticker = asset.ticker,
                    // *** PERBAIKAN AKHIR FINAL ***
                    // Menggunakan parameter 'lastPrice' karena komponen PortfolioItem belum diubah
                    marketValue = asset.marketValue,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomBar(navController: NavController, currentRoute: String) {
    NavigationBar(
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

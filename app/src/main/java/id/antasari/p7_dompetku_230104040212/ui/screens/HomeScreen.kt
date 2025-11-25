package id.antasari.p7_dompetku_230104040212.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.* import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.ui.res.painterResource
import androidx.annotation.DrawableRes
import id.antasari.p7_dompetku_230104040212.R

import id.antasari.p7_dompetku_230104040212.navigation.Destinations
import id.antasari.p7_dompetku_230104040212.ui.components.AppCard
import id.antasari.p7_dompetku_230104040212.ui.components.PortfolioItem

// ----------------------------------------------------
// 1. Data Model & Dummy Aset (Tidak Berubah)
// ----------------------------------------------------

data class Asset(
    val ticker: String,
    val name: String,
    val marketValue: String,
    val quantity: String,
    @DrawableRes val iconResId: Int,
    val iconTint: Color? = null
)

val dummyAssets = listOf(
    Asset("BTC", "Bitcoin", "Rp 1.450.000", "0.0007 BTC", R.drawable.bitcoin_logo, null),
    Asset("BBCA", "Bank Central Asia Tbk", "Rp 925.000", "100 Lembar", R.drawable.bbca_logo, null),
    Asset("CDIA", "Chandra Daya Investasi Tbk", "Rp 750.000", "400 Lembar", R.drawable.cdia_logo, null),
    Asset("SIMP", "Salim Ivomas Pratama Tbk", "Rp. 675.000", "1000 Lembar", R.drawable.simp_logo, null),
    Asset("IDR", "Uang Kas", "Rp 500.000", "1 Unit", R.drawable.rupiah_logo, null),
    Asset("NVDA", "Nvidia Inc", "Rp 130.000", "0.04 Lembar", R.drawable.nvidia_logo, null),
)

// Data Model & Dummy Quick Actions
data class QuickAction(
    val label: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

// MODIFIKASI: Mengganti "Aktivitas" dengan "Hapus Asset"
val portfolioActions: List<QuickAction> = listOf(
    QuickAction("Tambah Aset", Icons.Default.AddCircle, { /* Aksi Tambah Aset */ }),
    QuickAction("Hapus Asset", Icons.Default.RemoveCircle, { /* Aksi Hapus Asset */ }), // TELAH DIMODIFIKASI
    QuickAction("Laporan", Icons.Default.Assessment, { /* Aksi Buat Laporan */ }),
    QuickAction("Sinkronisasi", Icons.Default.Sync, { /* Aksi Sinkronisasi Data */ }),
)

// ----------------------------------------------------
// 2. Composable Screen Utama (Tidak Berubah)
// ----------------------------------------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Destinations.HOME_ROUTE

    var balanceHidden by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { /* Tidak ada TopAppBar */ },
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
            // MENGURANGI JARAK ANTAR ITEM LAZYCOLUMN
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // --- ITEM 1: HEADER KUSTOM ---
            item {
                CustomHomeHeader(navController, "M. Kaspul Anwar")
            }

            // --- ITEM 2: BALANCE CARD MODERN ---
            item {
                BalanceCard(
                    totalValue = "Rp 4.250.000",
                    isHidden = balanceHidden,
                    onToggleClick = { balanceHidden = !balanceHidden }
                )
            }

            // --- ITEM 3: QUICK ACTIONS ---
            item {
                QuickActionsRow(actions = portfolioActions)
            }

            // --- ITEM 4: Judul Daftar Aset (Revisi Judul) ---
            item {
                // Menghapus Spacer di sini agar lebih rapat
                Text(
                    text = "Daftar Aset Saya", // Judul diperbaiki
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }

            // --- ITEM 5: Daftar Aset Portofolio ---
            items(dummyAssets) { asset ->
                PortfolioItem(
                    icon = {
                        Image(
                            painter = painterResource(id = asset.iconResId),
                            contentDescription = asset.name,
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    ticker = asset.ticker,
                    name = asset.name,
                    marketValue = asset.marketValue,
                    quantity = asset.quantity
                )
                // Divider Dihapus (Sesuai permintaan)
            }
        }
    }
}

// ----------------------------------------------------
// 3. KOMPONEN HOMESCREEN (MODIFIKASI BALANCE CARD)
// ----------------------------------------------------

@Composable
fun CustomHomeHeader(navController: NavController, userName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Selamat Datang,",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = userName,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        // Ikon Notifikasi
        IconButton(onClick = { /* Aksi Notifikasi */ }) {
            Icon(Icons.Default.Notifications, contentDescription = "Notifikasi")
        }

        // Ikon Profil (Dipertahankan di Header)
        IconButton(onClick = { navController.navigate(Destinations.PROFILE_ROUTE) }) {
            Icon(Icons.Default.Person, contentDescription = "Profil", tint = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
fun BalanceCard(totalValue: String, isHidden: Boolean, onToggleClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp).fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Total Nilai Portofolio",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = if (isHidden) "••••••••••" else totalValue,
                        style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.ExtraBold),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                IconButton(onClick = onToggleClick) {
                    Icon(
                        imageVector = if (isHidden) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (isHidden) "Tampilkan Saldo" else "Sembunyikan Saldo",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = { /* Aksi Riwayat Transaksi */ }, // Komentar diperbarui
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Riwayat Transaksi") // TEKS TELAH DIMODIFIKASI
            }
        }
    }
}

@Composable
fun QuickActionsRow(actions: List<QuickAction>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Top
    ) {
        actions.forEach { action ->
            QuickActionButton(action)
        }
    }
}

@Composable
fun QuickActionButton(action: QuickAction) {
    Column(
        modifier = Modifier
            .clickable(onClick = action.onClick)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surfaceContainerHigh,
            modifier = Modifier.size(56.dp)
        ) {
            Icon(
                action.icon,
                contentDescription = action.label,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(12.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = action.label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1
        )
    }
}

// ----------------------------------------------------
// 4. Bottom Navigation Bar Component (Dibiarkan)
// ----------------------------------------------------

private data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
)

// Menghapus Destinations.PROFILE_ROUTE dari daftar item navigasi
private val bottomNavItems = listOf(
    BottomNavItem(Destinations.HOME_ROUTE, Icons.Default.Home, "Home"),
    // PROFILE_ROUTE Dihapus
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
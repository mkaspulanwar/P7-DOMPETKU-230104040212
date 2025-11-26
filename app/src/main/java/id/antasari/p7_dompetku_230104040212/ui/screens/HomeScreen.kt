package id.antasari.p7_dompetku_230104040212.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import id.antasari.p7_dompetku_230104040212.R
import id.antasari.p7_dompetku_230104040212.model.Asset
import id.antasari.p7_dompetku_230104040212.navigation.Destinations
import id.antasari.p7_dompetku_230104040212.ui.components.PortfolioItem
import id.antasari.p7_dompetku_230104040212.viewmodel.HomeViewModel

// ----------------------------------------------------
// 1. Data Model Quick Actions
// ----------------------------------------------------

data class QuickAction(
    val label: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

// ----------------------------------------------------
// 2. Composable Screen Utama
// ----------------------------------------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel()
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Destinations.HOME_ROUTE

    var balanceHidden by remember { mutableStateOf(false) }
    
    // Mengambil data assets dari ViewModel
    val assets = viewModel.assets
    
    // Mengambil total nilai portofolio yang dihitung otomatis
    val totalPortfolioValue = viewModel.totalPortfolioValue
    
    // State untuk dialog tambah aset
    var showAddAssetDialog by remember { mutableStateOf(false) }

    // Definisi Action Button di dalam composable agar bisa akses state
    val portfolioActions = remember(assets) {
        listOf(
            QuickAction("Tambah Aset", Icons.Default.AddCircle, { showAddAssetDialog = true }),
            QuickAction("Hapus Asset", Icons.Default.RemoveCircle, { 
                viewModel.removeLastAsset()
            }),
            QuickAction("Laporan", Icons.Default.Assessment, { /* Aksi Buat Laporan */ }),
            QuickAction("Sinkronisasi", Icons.Default.Sync, { /* Aksi Sinkronisasi Data */ }),
        )
    }

    if (showAddAssetDialog) {
        AddAssetDialog(
            onDismiss = { showAddAssetDialog = false },
            onAddAsset = { ticker, name, marketValue, quantity, category ->
                // Mapping kategori ke icon sederhana
                val iconResId = when(category) {
                    "Crypto" -> R.drawable.bitcoin_logo
                    "Saham" -> R.drawable.bbca_logo
                    else -> R.drawable.rupiah_logo
                }
                viewModel.addAsset(ticker, name, marketValue, quantity, category, iconResId)
                showAddAssetDialog = false
            }
        )
    }

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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            item { CustomHomeHeader(navController, "M. Kaspul Anwar") }
            item { BalanceCard(totalValue = totalPortfolioValue, isHidden = balanceHidden, onToggleClick = { balanceHidden = !balanceHidden }) }
            item { QuickActionsRow(actions = portfolioActions) }
            item { Text("Daftar Aset Saya", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), modifier = Modifier.padding(vertical = 4.dp)) }
            items(assets) { asset ->
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
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAssetDialog(
    onDismiss: () -> Unit,
    onAddAsset: (ticker: String, name: String, marketValue: String, quantity: String, category: String) -> Unit
) {
    var category by remember { mutableStateOf("Crypto") } // Default selection
    var expanded by remember { mutableStateOf(false) }
    val categories = listOf("Crypto", "Saham", "Kas")

    var ticker by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var marketValue by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Tambah Aset Baru") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                
                // --- DROPDOWN KATEGORI ---
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = category,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Kategori Aset") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        categories.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item) },
                                onClick = {
                                    category = item
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = ticker,
                    onValueChange = { ticker = it },
                    label = { Text("Ticker (misal: ETH)") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nama Aset") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = marketValue,
                    onValueChange = { if (it.all { char -> char.isDigit() }) marketValue = it },
                    label = { Text("Nilai Pasar (Angka: 100000)") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = { 
                        // Label dinamis berdasarkan kategori
                        val unitLabel = when(category) {
                            "Crypto" -> "0.005"
                            "Saham" -> "100 (Lembar)"
                            "Kas" -> "1000000 (Rp)"
                            else -> "Jumlah"
                        }
                        Text("Jumlah ($unitLabel)") 
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                if (ticker.isNotEmpty() && name.isNotEmpty() && marketValue.isNotEmpty()) {
                    onAddAsset(ticker, name, marketValue, quantity, category)
                }
            }) {
                Text("Simpan")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Batal")
            }
        }
    )
}

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

        IconButton(onClick = { /* Aksi Notifikasi */ }) {
            Icon(Icons.Default.Notifications, contentDescription = "Notifikasi")
        }

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
                onClick = { /* Aksi Riwayat Transaksi */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Riwayat Transaksi")
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
// 4. Bottom Navigation Bar Component
// ----------------------------------------------------

private data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
)

private val bottomNavItems = listOf(
    BottomNavItem(Destinations.HOME_ROUTE, Icons.Default.Home, "Home"),
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

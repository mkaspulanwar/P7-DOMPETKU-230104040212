package id.antasari.p7_dompetku_230104040212.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.CurrencyExchange

// =========================================================
// 1. Komponen AppCard (Card Total Portofolio)
// =========================================================
@Composable
fun AppCard(
    modifier: Modifier = Modifier.fillMaxWidth(),
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        // --- PERBAIKAN: MENGHILANGKAN SHADOW (ELEVATION) ---
        // Mengatur elevation menjadi 0.dp agar card terlihat flat dan bersih.
        elevation = cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(
            // Menggunakan warna primaryContainer yang sangat lembut (alpha 0.2f)
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f),
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            content = content
        )
    }
}

// =========================================================
// 2. Komponen PortfolioItem (Item dalam Daftar Aset)
// =========================================================
@Composable
fun PortfolioItem(
    icon: @Composable () -> Unit,
    name: String,
    ticker: String,
    marketValue: String,
    quantity: String
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 1. Ikon Aset
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    // Latar belakang ikon menggunakan warna kontainer primer yang sedikit lebih terang/tebal
                    MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
                    MaterialTheme.shapes.medium
                ),
            contentAlignment = Alignment.Center
        ) {
            icon()
        }
        Spacer(Modifier.width(16.dp))

        // 2. Nama & Ticker
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
            )
            Text(
                text = ticker,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // 3. Harga dan Perubahan
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = marketValue,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )

            // Menampilkan Quantity
            Text(
                text = quantity,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}
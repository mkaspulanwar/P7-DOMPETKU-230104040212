package id.antasari.p7_dompetku_230104040212.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight // <-- SOLUSI ERROR

@Composable
fun AppCard(
    modifier: Modifier = Modifier.fillMaxWidth(),
    content: @Composable ColumnScope.() -> Unit // Menggunakan ColumnScope untuk konten
) {
    ElevatedCard( // Menggunakan ElevatedCard untuk kesan modern dan elevation
        modifier = modifier,
        shape = MaterialTheme.shapes.large, // Menggunakan shape besar (16.dp)
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp), // Padding konsisten
            content = content
        )
    }
}

// --- Komponen Item Portofolio Khusus ---
@Composable
fun PortfolioItem(
    icon: @Composable () -> Unit,
    name: String,
    ticker: String,
    lastPrice: String,
    quantity: String // Revisi: Menerima Quantity
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
                text = lastPrice,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )

            // Revisi: Menampilkan Quantity sebagai pengganti persentase
            Text(
                text = quantity, // Menampilkan jumlah/unit aset
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.secondary // Boleh menggunakan warna sekunder
            )
        }
    }
}

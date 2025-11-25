package id.antasari.p7_dompetku_230104040212.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    leadingIcon: @Composable (() -> Unit)? = null,
    isPasswordToggle: Boolean = false // Tambahan untuk handling password
) {
    // Anda bisa menggunakan OutlinedTextField atau FilledTextField
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = leadingIcon,
        modifier = modifier,
        // Menggunakan shape dari Theme.kt
        shape = MaterialTheme.shapes.medium,
        // Mengubah warna focus sesuai Primary Color
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline
        )
        // Tambahan lain seperti keyboardOptions, visualTransformation untuk password, dll.
    )
}
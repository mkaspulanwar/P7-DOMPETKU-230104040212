package id.antasari.p7_dompetku_230104040212.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import id.antasari.p7_dompetku_230104040212.ui.components.AppTextField
import id.antasari.p7_dompetku_230104040212.ui.components.PrimaryButton

// Anotasi ini menghilangkan warning 'experimental API'
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("mkaspulanwar@gmail.com") }
    var password by remember { mutableStateOf("password123") }
    val isLoginEnabled = email.isNotEmpty() && password.length >= 6

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Masuk ke Dompetku") },
                // Menghilangkan koma trailing dan menggunakan anotasi
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Logo Aplikasi atau Ikon Besar
            Icon(
                Icons.Default.Person,
                contentDescription = "Login Icon",
                modifier = Modifier.size(96.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Selamat Datang Kembali",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Text Field Email/Username
            AppTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email / Username",
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = "Email Icon")
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Text Field Password
            // --- BAGIAN INI DIPERBAIKI ---
            OutlinedTextField( // Menggunakan OutlinedTextField standar (Asumsi AppTextField belum sempurna)
                value = password,
                onValueChange = { password = it },
                label = { Text("Kata Sandi") },
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = "Password Icon")
                },
                modifier = Modifier.fillMaxWidth(),
                // Ini adalah SOLUSI untuk error 'No parameter with name visualTransformation found'
                visualTransformation = PasswordVisualTransformation(),
                shape = MaterialTheme.shapes.medium,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                )
            )
            // --------------------------

            Spacer(modifier = Modifier.height(48.dp))

            // Tombol Login
            PrimaryButton(
                text = "Login ke Akun Saya",
                onClick = onLoginSuccess,
                enabled = isLoginEnabled
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = { /* TODO: Navigasi ke Register */ }) {
                Text("Belum punya akun? Daftar Sekarang")
            }
        }
    }
}
package id.antasari.p7_dompetku_230104040212.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import id.antasari.p7_dompetku_230104040212.data.local.UserPreferences

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val userPrefs = UserPreferences(application)

    // State untuk UI
    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var isLoginMode by mutableStateOf(true) // True = Mode Login, False = Mode Register
    var errorMessage by mutableStateOf<String?>(null)
    var isLoggedIn by mutableStateOf(false)

    init {
        // Cek status login saat ViewModel dibuat
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        isLoggedIn = userPrefs.isLoggedIn()
    }

    fun toggleMode() {
        isLoginMode = !isLoginMode
        errorMessage = null // Reset error saat ganti mode
    }

    fun performAuth(onSuccess: () -> Unit) {
        if (username.isBlank() || password.isBlank()) {
            errorMessage = "Username dan Password tidak boleh kosong"
            return
        }

        if (isLoginMode) {
            // LOGIKA LOGIN
            val registeredUser = userPrefs.getUser()
            if (registeredUser == null) {
                errorMessage = "Belum ada user terdaftar. Silakan daftar dulu."
            } else if (registeredUser.first == username && registeredUser.second == password) {
                userPrefs.setLoggedIn(true)
                isLoggedIn = true
                onSuccess()
            } else {
                errorMessage = "Username atau Password salah!"
            }
        } else {
            // LOGIKA REGISTER
            val existingUser = userPrefs.getUser()
            // Sederhana: Kita izinkan overwrite user lama atau cek dulu
            // Di sini kita asumsi single user untuk app ini (menimpa data lama)
            userPrefs.saveUser(username, password)
            userPrefs.setLoggedIn(true)
            isLoggedIn = true
            onSuccess()
        }
    }

    fun logout() {
        userPrefs.logout()
        isLoggedIn = false
        username = ""
        password = ""
    }
}

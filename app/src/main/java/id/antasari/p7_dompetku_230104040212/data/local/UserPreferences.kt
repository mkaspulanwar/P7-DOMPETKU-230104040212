package id.antasari.p7_dompetku_230104040212.data.local

import android.content.Context
import android.content.SharedPreferences

/**
 * Kelas helper untuk menyimpan data user menggunakan SharedPreferences.
 * Ini bertindak sebagai "Cache" sederhana.
 */
class UserPreferences(context: Context) {
    private val PREFS_NAME = "dompetku_user_prefs"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }

    // Simpan data registrasi user
    fun saveUser(username: String, password: String) {
        val editor = prefs.edit()
        editor.putString(KEY_USERNAME, username)
        editor.putString(KEY_PASSWORD, password)
        editor.apply()
    }

    // Ambil data user (mengembalikan Pair<Username, Password> atau null jika belum ada)
    fun getUser(): Pair<String, String>? {
        val username = prefs.getString(KEY_USERNAME, null)
        val password = prefs.getString(KEY_PASSWORD, null)
        
        if (username != null && password != null) {
            return Pair(username, password)
        }
        return null
    }

    // Set status login
    fun setLoggedIn(isLoggedIn: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
        editor.apply()
    }

    // Cek apakah user sedang login
    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    // Fungsi logout (menghapus status login, tapi data user tetap ada agar mudah login lagi)
    fun logout() {
        setLoggedIn(false)
    }
}

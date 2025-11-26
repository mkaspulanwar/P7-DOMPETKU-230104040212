# P7-DOMPETKU-230104040212: IMPLEMENTASI DESAIN UI MODERN (MATERIAL 3)

### Informasi Proyek

| Keterangan | Detail |
| :--- | :--- |
| **Mata Kuliah** | Mobile Programming 20251 |
| **Modul** | Praktikum #7: Menerapkan Desain UI Modern |
| **Topik Utama** | Material Design 3, Style, Theme, & Modern UI Principles |
| **Dosen Pengampu** | Muhayat, M.IT |
| **Durasi** | 1 x 120 menit |
| **Deadline** | Selasa, 25 November 2025 |

---

### Tujuan Praktikum

Tujuan utama dari praktikum ini adalah menerapkan prinsip Material Design 3 dan melakukan *UI refinement* agar aplikasi tampil profesional. Secara rinci, tujuannya meliputi:

* Menjelaskan prinsip desain UI modern sesuai Material Design 3.
* Mengimplementasikan color scheme, typography, dan shape pada aplikasi Android.
* Membuat file theme dan style yang modular, reusable, dan konsisten.
* Menerapkan komponen Material 3 (Button, Card, TextField, AppBar).
* Menggunakan Dynamic Color & Custom Color Palette.
* Menerapkan dark mode dan light mode secara benar.

---

### Hasil Implementasi & Tugas

Proyek ini telah menyelesaikan empat tugas praktikum yang wajib dikumpulkan:

#### **Tugas 1: Membuat Design System Mini**
Berhasil membuat struktur folder desain yang rapi dan mengimplementasikan file-file utama tema:
* \`Color.kt\`
* \`Type.kt\`
* \`Shape.kt\`
* \`Theme.kt\`

#### **Tugas 2: Membuat 3 Reusable Components**
Telah dibuat tiga komponen yang dapat digunakan kembali (*reusable components*):
1.  PrimaryButton
2.  AppCard
3.  AppTextField

#### **Tugas 3: Modernizing Screen**
Semua halaman utama telah diubah menjadi UI modern (Material 3) dengan tipografi rapi dan konsistensi warna, serta mendukung Dark/Light mode. Halaman yang dimodernisasi meliputi:
* Login/Register
* Home (Halaman ini telah melalui proses *refinement* final untuk minimalisir elemen)
* Profile
* Settings

**Modifikasi Desain Spesifik di HomeScreen (Hasil Refinement):**
* **Bottom Navigation Bar:** Hanya menyertakan 'Home' dan 'Settings'. Menu 'Profile' dipindahkan ke Header.
* **Balance Card:** Tombol penuh 'Lihat Detail Portofolio' diganti menjadi tombol **'Riwayat Transaksi'** untuk fokus pada fungsionalitas utama.
* **Quick Actions:** Aksi 'Aktivitas' diganti menjadi **'Hapus Asset'**, menyesuaikan kebutuhan aplikasi *tracker* yang minimalis.

#### **Tugas 4: Demo Video**
Membuat video demo berdurasi 1 menit yang wajib menunjukkan:
* Transisi Light mode → Dark mode.
* Tampilan halaman yang telah diperbarui.
* Penjelasan singkat mengenai perbaikan UI yang dilakukan.

---

### Struktur Folder Proyek

Struktur folder utama mengikuti panduan output praktikum:

```bash
p7_dompetku_230104040212/
├── screenshots/
│   ├── light_mode.png
│   ├── dark_mode.png
│   └── modernized_screen.png
├── \src/
│   └── id/antasari/p7_dompetku_230104040212/
│       ├── navigation/
│       │   └── AppNavHost.kt
│       └── ui/
│           ├── components/
│           │   ├── AppButton.kt
│           │   ├── AppCard.kt
│           │   └── AppTextField.kt
│           ├── screens/
│           │   ├── HomeScreen.kt
│           │   ├── LoginScreen.kt
│           │   ├── ProfileScreen.kt
│           │   └── SettingsScreen.kt
│           └── theme/
│               ├── Color.kt
│               ├── Shape.kt
│               ├── Theme.kt
│               └── Type.kt
└── ...files lainnya
```

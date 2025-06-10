package com.example.dashboard_ewaste_android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp // Anotasi ini memberi tahu Hilt untuk membuat komponen aplikasi
class EwasteApplication : Application() {
    // Anda bisa menambahkan inisialisasi lain di sini jika diperlukan
}
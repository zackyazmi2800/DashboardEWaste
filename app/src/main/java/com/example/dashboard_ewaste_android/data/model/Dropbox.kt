package com.example.dashboard_ewaste_android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dropbox")
data class Dropbox(
    @PrimaryKey(autoGenerate = true)
    val idDropbox: Int = 0,  // Id_Dropbox: int
    val nama: String,       // nama: String
    val lokasi: String,     // lokasi: String
    val kapasitas: Int      // kapasitas: int
)
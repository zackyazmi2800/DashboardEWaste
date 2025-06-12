package com.example.dashboard_ewaste_android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "waste_items")
data class WasteItem(
    @PrimaryKey(autoGenerate = true)
    val idSampah: Int = 0, // Id_Sampah: int
    val nama: String,     // nama: String
    val berat: Int,       // berat: int
    val qty: Int          // QTY: int
)
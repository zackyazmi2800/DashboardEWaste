package com.example.dashboard_ewaste_android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "poin")
data class Poin(
    @PrimaryKey(autoGenerate = true)
    val idPoin: Int = 0,    // Id Poin: int
    val jumlahPoin: Int     // Jumlah_Poin: int
)

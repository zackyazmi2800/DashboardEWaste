package com.example.dashboard_ewaste_android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "berkas_detail")
data class BerkasDetail(
    @PrimaryKey(autoGenerate = true)
    val idBerkas: Int = 0, // Id Berkas: int
    val namaBerkas: String, // nama_Berkas: String
    val tipe: String,       // tipe: String
    val size: Int           // size: Int
)
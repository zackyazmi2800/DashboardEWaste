package com.example.dashboard_ewaste_android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "approvals") // Nama tabel di database
data class Approval(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L, // ID unik untuk setiap permintaan approval
    val namaPengaju: String, // Nama individu atau kurir yang mengajukan
    val tipeApproval: String, // Contoh: "Masyarakat", "Kurir"
    val status: String = "Pending", // Status: "Pending", "Approved", "Rejected"
    val tanggalPengajuan: Long = System.currentTimeMillis() // Tanggal pengajuan dalam milidetik
    // Anda bisa menambahkan properti lain seperti detail berkas jika diperlukan
    // val berkasDetailId: Long? = null // Jika ada relasi ke BerkasDetail
)
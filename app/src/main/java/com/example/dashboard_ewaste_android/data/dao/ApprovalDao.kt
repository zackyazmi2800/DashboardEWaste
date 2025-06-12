package com.example.dashboard_ewaste_android.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.dashboard_ewaste_android.data.model.Approval
import kotlinx.coroutines.flow.Flow

@Dao
interface ApprovalDao {
    @Query("SELECT * FROM approvals ORDER BY tanggalPengajuan DESC") // Mengambil semua data approval, diurutkan terbaru
    fun getAllApprovals(): Flow<List<Approval>>

    @Insert(onConflict = OnConflictStrategy.REPLACE) // Memasukkan data baru atau mengganti yang sudah ada
    suspend fun insertApproval(approval: Approval)

    @Update // Memperbarui data approval yang sudah ada
    suspend fun updateApproval(approval: Approval)

    @Query("DELETE FROM approvals WHERE id = :approvalId") // Menghapus approval berdasarkan ID
    suspend fun deleteApprovalById(approvalId: Long)

    @Query("DELETE FROM approvals") // Menghapus semua approval (untuk pengujian)
    suspend fun deleteAllApprovals()
}
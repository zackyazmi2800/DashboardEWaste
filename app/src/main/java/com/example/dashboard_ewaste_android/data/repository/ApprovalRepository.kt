package com.example.dashboard_ewaste_android.data.repository

import com.example.dashboard_ewaste_android.data.dao.ApprovalDao // <-- Impor DAO
import com.example.dashboard_ewaste_android.data.model.Approval // <-- Impor model
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton // Hanya satu instance Repository ini di seluruh aplikasi
class ApprovalRepository @Inject constructor(
    private val approvalDao: ApprovalDao // Hilt akan menyediakan instance DAO ini
) {
    fun getAllApprovals(): Flow<List<Approval>> {
        return approvalDao.getAllApprovals()
    }

    suspend fun addApproval(approval: Approval) {
        approvalDao.insertApproval(approval)
    }

    suspend fun updateApproval(approval: Approval) {
        approvalDao.updateApproval(approval)
    }

    suspend fun deleteApprovalById(approvalId: Long) {
        approvalDao.deleteApprovalById(approvalId)
    }

    suspend fun deleteAllApprovals() {
        approvalDao.deleteAllApprovals()
    }

    // Di sinilah nanti Anda akan menambahkan fungsi untuk interaksi API,
    // misalnya: suspend fun getApprovalsFromApi(): List<Approval> { ... }
    // dan kemudian mengintegrasikannya dengan database lokal.
}
package com.example.dashboard_ewaste_android.ui.screens.approval

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dashboard_ewaste_android.data.model.Approval
import com.example.dashboard_ewaste_android.data.repository.ApprovalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class ApprovalViewModel @Inject constructor(
    private val repository: ApprovalRepository // Hilt akan menyediakan instance Repository ini
) : ViewModel() {

    // StateFlow untuk daftar approval yang akan diobservasi oleh UI
    private val _approvals = MutableStateFlow<List<Approval>>(emptyList())
    val approvals: StateFlow<List<Approval>> = _approvals.asStateFlow()

    init {
        // Mengumpulkan semua approval saat ViewModel dibuat
        viewModelScope.launch {
            repository.getAllApprovals().collect {
                _approvals.value = it // Perbarui StateFlow saat data berubah
            }
        }

        // Tambahkan beberapa data dummy untuk pengujian awal
        // Anda bisa menghapus ini setelah integrasi API atau inputan UI
        viewModelScope.launch {
            if (repository.getAllApprovals().first().isEmpty()) { // Hanya tambahkan jika database kosong
                repository.addApproval(Approval(namaPengaju = "Zacky Azmi", tipeApproval = "Masyarakat"))
                repository.addApproval(Approval(namaPengaju = "Aldi Maulana Fadilah", tipeApproval = "Kurir"))
                repository.addApproval(Approval(namaPengaju = "Umar", tipeApproval = "Masyarakat"))
            }
        }
    }

    // Fungsi untuk menyetujui approval
    fun approveApproval(approval: Approval) {
        viewModelScope.launch {
            repository.updateApproval(approval.copy(status = "Approved"))
        }
    }

    // Fungsi untuk menolak approval
    fun rejectApproval(approval: Approval) {
        viewModelScope.launch {
            repository.updateApproval(approval.copy(status = "Rejected"))
        }
    }

    // Fungsi untuk menambahkan approval baru (misalnya dari admin atau API)
    fun addApproval(approval: Approval) {
        viewModelScope.launch {
            repository.addApproval(approval)
        }
    }

    // Fungsi untuk menghapus approval
    fun deleteApproval(approvalId: Long) {
        viewModelScope.launch {
            repository.deleteApprovalById(approvalId)
        }
    }

    // Fungsi untuk menghapus semua approval (untuk pengujian/reset)
    fun deleteAllApprovals() {
        viewModelScope.launch {
            repository.deleteAllApprovals()
        }
    }
}
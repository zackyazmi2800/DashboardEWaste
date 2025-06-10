package com.example.dashboard_ewaste_android.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dashboard_ewaste_android.data.model.Dropbox
import com.example.dashboard_ewaste_android.data.model.Poin
import com.example.dashboard_ewaste_android.data.model.WasteItem
import com.example.dashboard_ewaste_android.data.repository.DropboxRepository
import com.example.dashboard_ewaste_android.data.repository.PoinRepository
import com.example.dashboard_ewaste_android.data.repository.WasteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dropboxRepository: DropboxRepository,
    private val poinRepository: PoinRepository,
    private val wasteRepository: WasteRepository
) : ViewModel() {

    // State untuk data yang akan ditampilkan di Dashboard
    private val _allDropboxes = MutableStateFlow<List<Dropbox>>(emptyList())
    val allDropboxes: StateFlow<List<Dropbox>> = _allDropboxes.asStateFlow()

    private val _allPoin = MutableStateFlow<List<Poin>>(emptyList())
    val allPoin: StateFlow<List<Poin>> = _allPoin.asStateFlow()

    private val _allWasteItems = MutableStateFlow<List<WasteItem>>(emptyList())
    val allWasteItems: StateFlow<List<WasteItem>> = _allWasteItems.asStateFlow()

    init {
        // Mengumpulkan data dari semua repository
        viewModelScope.launch {
            dropboxRepository.getDropboxes().collect { _allDropboxes.value = it }
        }
        viewModelScope.launch {
            poinRepository.getAllPoin().collect { _allPoin.value = it }
        }
        viewModelScope.launch {
            wasteRepository.getWasteItems().collect { _allWasteItems.value = it }
        }
    }

}


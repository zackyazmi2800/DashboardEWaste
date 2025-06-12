package com.example.dashboard_ewaste_android.ui.screens.dashboard

import android.util.Log
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
import kotlinx.coroutines.flow.first // <--- PASTIKAN INI DIIMPOR
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dropboxRepository: DropboxRepository,
    private val poinRepository: PoinRepository,
    private val wasteRepository: WasteRepository
) : ViewModel() {

    private val _allDropboxes = MutableStateFlow<List<Dropbox>>(emptyList())
    val allDropboxes: StateFlow<List<Dropbox>> = _allDropboxes.asStateFlow()

    private val _allPoin = MutableStateFlow<List<Poin>>(emptyList())
    val allPoin: StateFlow<List<Poin>> = _allPoin.asStateFlow()

    private val _allWasteItems = MutableStateFlow<List<WasteItem>>(emptyList())
    val allWasteItems: StateFlow<List<WasteItem>> = _allWasteItems.asStateFlow()

    init {
        Log.d("DashboardViewModel", "ViewModel initialized.")
    }

    fun loadAllData() {
        Log.d("DashboardViewModel", "loadAllData() called from UI. Starting collections.")
        viewModelScope.launch {
            try {
                Log.d("DashboardViewModel", "Collecting dropboxes for Dashboard...")
                val loadedDropboxes = dropboxRepository.getDropboxes().first() // <--- PERUBAHAN DI SINI
                _allDropboxes.value = loadedDropboxes
                Log.d("DashboardViewModel", "Collected ${loadedDropboxes.size} dropboxes for Dashboard.")
            } catch (e: Exception) {
                Log.e("DashboardViewModel", "Error loading dropboxes for Dashboard: ${e.message}", e)
                _allDropboxes.value = emptyList()
            }
        }
        viewModelScope.launch {
            Log.d("DashboardViewModel", "Collecting poin for Dashboard...")
            poinRepository.getAllPoin().collect {
                Log.d("DashboardViewModel", "Collected ${it.size} poin for Dashboard.")
                _allPoin.value = it
            }
        }
        viewModelScope.launch {
            Log.d("DashboardViewModel", "Collecting waste items for Dashboard...")
            wasteRepository.getWasteItems().collect {
                Log.d("DashboardViewModel", "Collected ${it.size} waste items for Dashboard.")
                _allWasteItems.value = it
            }
        }
    }
}
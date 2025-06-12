package com.example.dashboard_ewaste_android.ui.screens.waste

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dashboard_ewaste_android.data.model.WasteItem
import com.example.dashboard_ewaste_android.data.repository.WasteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first // Pastikan ini diimpor
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WasteViewModel @Inject constructor(
    private val repository: WasteRepository
) : ViewModel() {

    private val _wasteItems = MutableStateFlow<List<WasteItem>>(emptyList())
    val wasteItems: StateFlow<List<WasteItem>> = _wasteItems.asStateFlow()

    init {
        Log.d("WasteViewModel", "ViewModel initialized.")
        // loadWasteItems() # Tidak perlu dipanggil di init, akan dipanggil dari LaunchedEffect di UI
        // ^^^ UBAH BARIS INI DARI # MENJADI // UNTUK KOMENTAR YANG BENAR
    }

    fun loadWasteItems() {
        Log.d("WasteViewModel", "loadWasteItems() called from UI. Starting collection.")
        viewModelScope.launch {
            try {
                val loadedItems = repository.getWasteItems().first()
                _wasteItems.value = loadedItems
                Log.d("WasteViewModel", "Collected ${loadedItems.size} waste items.")
            } catch (e: Exception) {
                Log.e("WasteViewModel", "Error loading waste items: ${e.message}", e)
                _wasteItems.value = emptyList()
            }
        }
    }

    suspend fun addWasteItem(wasteItem: WasteItem) {
        Log.d("WasteViewModel", "Adding WasteItem: ${wasteItem.nama}")
        viewModelScope.launch {
            repository.addWasteItem(wasteItem)
            loadWasteItems()
        }
    }
}
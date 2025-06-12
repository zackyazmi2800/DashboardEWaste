package com.example.dashboard_ewaste_android.ui.screens.dropbox

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dashboard_ewaste_android.data.model.Dropbox
import com.example.dashboard_ewaste_android.data.repository.DropboxRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DropboxViewModel @Inject constructor(
    private val repository: DropboxRepository
) : ViewModel() {

    private val _dropboxItems = MutableStateFlow<List<Dropbox>>(emptyList())
    val dropboxItems: StateFlow<List<Dropbox>> = _dropboxItems.asStateFlow()

    init {
        Log.d("DropboxViewModel", "ViewModel initialized.")
    }

    fun loadDropboxes() {
        Log.d("DropboxViewModel", "loadDropboxes() called from UI. Starting collection.")
        viewModelScope.launch {
            try {
                val loadedItems = repository.getDropboxes().first()
                _dropboxItems.value = loadedItems
                Log.d("DropboxViewModel", "Successfully loaded and set ${loadedItems.size} dropboxes to StateFlow.")
            } catch (e: Exception) {
                Log.e("DropboxViewModel", "Error loading dropboxes: ${e.message}", e)
                _dropboxItems.value = emptyList()
            }
        }
    }

    suspend fun addDropbox(dropbox: Dropbox) {
        Log.d("DropboxViewModel", "Adding Dropbox: ${dropbox.nama}")
        repository.addDropbox(dropbox)
        loadDropboxes()
    }

    fun deleteDropbox(dropbox: Dropbox) { // <--- TAMBAHKAN FUNGSI INI
        Log.d("DropboxViewModel", "Deleting Dropbox: ${dropbox.nama}")
        viewModelScope.launch {
            repository.deleteDropbox(dropbox)
            loadDropboxes() // Refresh daftar setelah menghapus
        }
    }
}
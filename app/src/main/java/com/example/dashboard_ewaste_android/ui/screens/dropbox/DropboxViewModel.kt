package com.example.dashboard_ewaste_android.ui.screens.dropbox

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dashboard_ewaste_android.data.model.Dropbox
import com.example.dashboard_ewaste_android.data.repository.DropboxRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DropboxViewModel @Inject constructor(
    private val repository: DropboxRepository
) : ViewModel() {

    private val _dropboxItems = MutableStateFlow<List<Dropbox>>(emptyList())
    val dropboxItems: StateFlow<List<Dropbox>> = _dropboxItems.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getDropboxes().collect {
                _dropboxItems.value = it
            }
        }
    }

    fun addDropbox(dropbox: Dropbox) {
        viewModelScope.launch {
            repository.addDropbox(dropbox)
        }
    }

}
package com.example.dashboard_ewaste_android.ui.screens.waste

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dashboard_ewaste_android.data.model.WasteItem
import com.example.dashboard_ewaste_android.data.repository.WasteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WasteViewModel @Inject constructor(
    private val repository: WasteRepository
) : ViewModel() {

    private val _wasteItems = MutableStateFlow<List<WasteItem>>(emptyList())
    val wasteItems: StateFlow<List<WasteItem>> = _wasteItems.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getWasteItems().collect {
                _wasteItems.value = it
            }
        }
    }

    fun addWasteItem(wasteItem: WasteItem) {
        viewModelScope.launch {
            repository.addWasteItem(wasteItem)
        }
    }
}
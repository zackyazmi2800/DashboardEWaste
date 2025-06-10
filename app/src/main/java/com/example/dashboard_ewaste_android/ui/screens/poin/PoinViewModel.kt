package com.example.dashboard_ewaste_android.ui.screens.poin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dashboard_ewaste_android.data.model.Poin
import com.example.dashboard_ewaste_android.data.repository.PoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PoinViewModel @Inject constructor(
    private val repository: PoinRepository
) : ViewModel() {

    private val _poinData = MutableStateFlow<List<Poin>>(emptyList())
    val poinData: StateFlow<List<Poin>> = _poinData.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllPoin().collect {
                _poinData.value = it
            }
        }
    }

    fun addPoin(poin: Poin) {
        viewModelScope.launch {
            repository.addPoin(poin)
        }
    }

}
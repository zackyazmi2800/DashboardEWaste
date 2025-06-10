package com.example.dashboard_ewaste_android.data.repository

import com.example.dashboard_ewaste_android.data.dao.WasteItemDao
import com.example.dashboard_ewaste_android.data.model.WasteItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WasteRepository @Inject constructor(
    private val wasteItemDao: WasteItemDao
) {
    fun getWasteItems(): Flow<List<WasteItem>> {
        return wasteItemDao.getAllWasteItems()
    }

    suspend fun addWasteItem(wasteItem: WasteItem) {
        wasteItemDao.insertWasteItem(wasteItem)
    }
}
package com.example.dashboard_ewaste_android.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dashboard_ewaste_android.data.model.WasteItem
import kotlinx.coroutines.flow.Flow

@Dao
interface WasteItemDao {
    @Query("SELECT * FROM waste_items")
    fun getAllWasteItems(): Flow<List<WasteItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWasteItem(wasteItem: WasteItem)
}
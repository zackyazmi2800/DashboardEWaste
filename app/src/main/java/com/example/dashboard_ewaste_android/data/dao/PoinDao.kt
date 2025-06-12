package com.example.dashboard_ewaste_android.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dashboard_ewaste_android.data.model.Poin
import kotlinx.coroutines.flow.Flow

@Dao
interface PoinDao {
    @Query("SELECT * FROM poin")
    fun getAllPoin(): Flow<List<Poin>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPoin(poin: Poin)
}
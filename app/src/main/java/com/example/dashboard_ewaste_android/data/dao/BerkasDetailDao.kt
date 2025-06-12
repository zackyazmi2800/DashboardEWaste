package com.example.dashboard_ewaste_android.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dashboard_ewaste_android.data.model.BerkasDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface BerkasDetailDao {
    @Query("SELECT * FROM berkas_detail")
    fun getAllBerkasDetail(): Flow<List<BerkasDetail>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBerkasDetail(berkasDetail: BerkasDetail)
}
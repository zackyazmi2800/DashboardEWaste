package com.example.dashboard_ewaste_android.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dashboard_ewaste_android.data.model.Dropbox
import kotlinx.coroutines.flow.Flow

@Dao
interface DropboxDao {
    @Query("SELECT * FROM dropbox")
    fun getAllDropboxes(): Flow<List<Dropbox>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDropbox(dropbox: Dropbox)
}
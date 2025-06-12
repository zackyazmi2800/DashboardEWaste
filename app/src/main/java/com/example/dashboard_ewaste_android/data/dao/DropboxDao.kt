package com.example.dashboard_ewaste_android.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete // <--- TAMBAHKAN IMPOR INI
import com.example.dashboard_ewaste_android.data.model.Dropbox
import kotlinx.coroutines.flow.Flow

@Dao
interface DropboxDao {
    @Query("SELECT * FROM dropbox")
    fun getAllDropboxes(): Flow<List<Dropbox>>

    @Insert
    suspend fun insertDropbox(dropbox: Dropbox)

    @Query("DELETE FROM dropbox")
    suspend fun deleteAllDropboxes()

    @Delete // <--- TAMBAHKAN FUNGSI INI
    suspend fun deleteDropbox(dropbox: Dropbox)
}
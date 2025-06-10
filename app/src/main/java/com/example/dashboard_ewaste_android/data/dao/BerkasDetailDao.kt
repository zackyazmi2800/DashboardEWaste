package com.example.dashboard_ewaste_android.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dashboard_ewaste_android.data.model.BerkasDetail
import kotlinx.coroutines.flow.Flow // Untuk mengamati perubahan data secara reaktif

@Dao
interface BerkasDetailDao {
    @Query("SELECT * FROM berkas_detail") // Query SQL untuk mengambil semua BerkasDetail
    fun getAllBerkasDetail(): Flow<List<BerkasDetail>>

    @Insert(onConflict = OnConflictStrategy.REPLACE) // Jika ada konflik (misal ID sama), ganti yang lama
    suspend fun insertBerkasDetail(berkasDetail: BerkasDetail) // Fungsi untuk memasukkan data baru
}
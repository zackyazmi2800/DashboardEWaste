package com.example.dashboard_ewaste_android.data.repository

import android.util.Log
import com.example.dashboard_ewaste_android.data.dao.DropboxDao
import com.example.dashboard_ewaste_android.data.model.Dropbox
import com.example.dashboard_ewaste_android.data.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DropboxRepository @Inject constructor(
    private val dropboxDao: DropboxDao,
    private val apiService: ApiService
) {
    fun getDropboxes(): Flow<List<Dropbox>> = flow {
        Log.d("DropboxRepo", "getDropboxes() called")
        try {
            Log.d("DropboxRepo", "Attempting to fetch from API service (mock)...")
            val response = apiService.getDropboxes()
            if (response.isSuccessful && response.body() != null) {
                val dropboxesFromApi = response.body()!!
                Log.d("DropboxRepo", "Fetched ${dropboxesFromApi.size} dropboxes from API (mock).")

                dropboxDao.deleteAllDropboxes()
                dropboxesFromApi.forEach { dropboxDao.insertDropbox(it) }
                Log.d("DropboxRepo", "Persisted API (mock) data to local Room DB.")
            } else {
                Log.d("DropboxRepo", "API (mock) response not successful or body is null. Code: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e("DropboxRepo", "Exception during API (mock) fetch: ${e.message}", e)
        }
        val localDropboxes = dropboxDao.getAllDropboxes().first()
        Log.d("DropboxRepo", "Emitting ${localDropboxes.size} dropboxes from Room DB.")
        emit(localDropboxes)
    }

    suspend fun addDropbox(dropbox: Dropbox) {
        Log.d("DropboxRepo", "addDropbox() called for: ${dropbox.nama}")
        dropboxDao.insertDropbox(dropbox)
        // Di sini nanti bisa tambahkan panggilan POST ke API server
    }

    suspend fun deleteDropbox(dropbox: Dropbox) { // <--- TAMBAHKAN FUNGSI INI
        Log.d("DropboxRepo", "deleteDropbox() called for: ${dropbox.nama}")
        dropboxDao.deleteDropbox(dropbox)
        // Di sini nanti bisa tambahkan panggilan DELETE ke API server
    }

    fun getDropboxesFromLocalDb(): Flow<List<Dropbox>> {
        return dropboxDao.getAllDropboxes()
    }
}
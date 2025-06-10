package com.example.dashboard_ewaste_android.data.repository

import com.example.dashboard_ewaste_android.data.dao.DropboxDao
import com.example.dashboard_ewaste_android.data.model.Dropbox
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DropboxRepository @Inject constructor(
    private val dropboxDao: DropboxDao
) {
    fun getDropboxes(): Flow<List<Dropbox>> {
        return dropboxDao.getAllDropboxes()
    }

    suspend fun addDropbox(dropbox: Dropbox) {
        dropboxDao.insertDropbox(dropbox)
    }
}
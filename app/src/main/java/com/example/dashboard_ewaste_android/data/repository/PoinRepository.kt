package com.example.dashboard_ewaste_android.data.repository

import com.example.dashboard_ewaste_android.data.dao.PoinDao
import com.example.dashboard_ewaste_android.data.model.Poin
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PoinRepository @Inject constructor(
    private val poinDao: PoinDao
) {
    fun getAllPoin(): Flow<List<Poin>> {
        return poinDao.getAllPoin()
    }

    suspend fun addPoin(poin: Poin) {
        poinDao.insertPoin(poin)
    }
}
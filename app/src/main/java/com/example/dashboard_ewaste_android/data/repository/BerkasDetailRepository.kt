package com.example.dashboard_ewaste_android.data.repository

import com.example.dashboard_ewaste_android.data.dao.BerkasDetailDao
import com.example.dashboard_ewaste_android.data.model.BerkasDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BerkasDetailRepository @Inject constructor(
    private val berkasDetailDao: BerkasDetailDao // Hilt akan menyediakan instance DAO ini
) {
    fun getBerkasDetails(): Flow<List<BerkasDetail>> {
        return berkasDetailDao.getAllBerkasDetail()
    }

    suspend fun addBerkasDetail(berkasDetail: BerkasDetail) {
        berkasDetailDao.insertBerkasDetail(berkasDetail)
    }


}
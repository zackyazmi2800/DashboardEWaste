package com.example.dashboard_ewaste_android.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dashboard_ewaste_android.data.dao.ApprovalDao
import com.example.dashboard_ewaste_android.data.dao.BerkasDetailDao
import com.example.dashboard_ewaste_android.data.dao.DropboxDao
import com.example.dashboard_ewaste_android.data.dao.PoinDao
import com.example.dashboard_ewaste_android.data.dao.WasteItemDao
import com.example.dashboard_ewaste_android.data.model.Approval
import com.example.dashboard_ewaste_android.data.model.BerkasDetail
import com.example.dashboard_ewaste_android.data.model.Dropbox
import com.example.dashboard_ewaste_android.data.model.Poin
import com.example.dashboard_ewaste_android.data.model.WasteItem

@Database(
    entities = [
        Approval::class,
        BerkasDetail::class,
        Dropbox::class,
        Poin::class,
        WasteItem::class
    ],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    // Deklarasikan semua DAO Anda di sini sebagai fungsi abstract
    abstract fun approvalDao(): ApprovalDao
    abstract fun berkasDetailDao(): BerkasDetailDao
    abstract fun dropboxDao(): DropboxDao
    abstract fun poinDao(): PoinDao
    abstract fun wasteItemDao(): WasteItemDao
}
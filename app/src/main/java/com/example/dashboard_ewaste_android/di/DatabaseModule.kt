package com.example.dashboard_ewaste_android.di

import android.content.Context
import androidx.room.Room
import com.example.dashboard_ewaste_android.data.AppDatabase
import com.example.dashboard_ewaste_android.data.dao.BerkasDetailDao
import com.example.dashboard_ewaste_android.data.dao.DropboxDao
import com.example.dashboard_ewaste_android.data.dao.PoinDao
import com.example.dashboard_ewaste_android.data.dao.WasteItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module // Ini adalah modul Dagger Hilt
@InstallIn(SingletonComponent::class) // Modul ini akan tersedia selama aplikasi hidup (singleton)
object DatabaseModule { // Menggunakan 'object' karena semua fungsi adalah @Provides statis

    @Singleton // Hanya akan ada satu instance database ini di seluruh aplikasi
    @Provides // Memberi tahu Hilt bahwa fungsi ini menyediakan dependensi
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "ewaste_database" // Nama file database Anda
        ).build()
    }

    // Menyediakan instance DAO. Hilt akan otomatis menyediakan AppDatabase
    @Singleton
    @Provides
    fun provideBerkasDetailDao(database: AppDatabase): BerkasDetailDao {
        return database.berkasDetailDao()
    }

    @Singleton
    @Provides
    fun provideDropboxDao(database: AppDatabase): DropboxDao {
        return database.dropboxDao()
    }

    @Singleton
    @Provides
    fun providePoinDao(database: AppDatabase): PoinDao {
        return database.poinDao()
    }

    @Singleton
    @Provides
    fun provideWasteItemDao(database: AppDatabase): WasteItemDao {
        return database.wasteItemDao()
    }
}
package com.example.dashboard_ewaste_android.di.network // Sesuaikan dengan package name Anda

import com.example.dashboard_ewaste_android.data.network.ApiService // Impor ApiService
import com.example.dashboard_ewaste_android.data.network.MockInterceptor // Impor MockInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor // Impor Logging Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory // Impor GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Modul ini akan tersedia selama siklus hidup aplikasi
object NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        // Interceptor untuk logging permintaan dan respons HTTP
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY) // Log body permintaan dan respons
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        mockInterceptor: MockInterceptor // Hilt akan menyediakan MockInterceptor yang sudah kita buat
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor) // Tambahkan logging
            .addInterceptor(mockInterceptor) // <--- Tambahkan MockInterceptor Anda di sini
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        // BASE_URL ini tidak terlalu penting untuk mocking karena Interceptor yang menangani,
        // tapi Retrofit tetap membutuhkannya.
        val BASE_URL = "http://localhost/" // URL dasar dummy

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient) // Gunakan OkHttpClient dengan interceptor kita
            .addConverterFactory(GsonConverterFactory.create()) // Konverter JSON
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java) // Membuat implementasi ApiService
    }
}
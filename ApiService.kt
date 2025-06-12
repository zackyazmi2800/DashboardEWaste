package com.example.dashboard_ewaste_android.data.network

import com.example.dashboard_ewaste_android.data.model.Dropbox
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("dropboxes")
    suspend fun getDropboxes(): Response<List<Dropbox>>
}
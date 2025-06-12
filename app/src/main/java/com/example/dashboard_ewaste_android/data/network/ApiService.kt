package com.example.dashboard_ewaste_android.data.network // Sesuaikan dengan package name Anda

import com.example.dashboard_ewaste_android.data.model.Dropbox
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("dropboxes") // Endpoint yang akan dipanggil (misalnya, /api/dropboxes)
    suspend fun getDropboxes(): Response<List<Dropbox>> // Mengembalikan daftar Dropbox

    // Anda bisa menambahkan fungsi lain untuk entitas atau operasi lain di sini, misalnya:
    // @GET("approvals")
    // suspend fun getApprovals(): Response<List<Approval>>

    // @POST("dropboxes")
    // suspend fun createDropbox(@Body dropbox: Dropbox): Response<Dropbox>
}
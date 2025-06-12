package com.example.dashboard_ewaste_android.data.network // Sesuaikan dengan package name Anda

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MockInterceptor @Inject constructor(
    @ApplicationContext private val context: Context // Hilt akan menyediakan Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url.toUri() // Dapatkan URI dari request
        val responseString: String

        // Logika untuk membaca JSON berdasarkan path URI
        return when {
            uri.path.contains("dropboxes") -> { // Jika path mengandung "dropboxes"
                responseString = context.assets.open("dropboxes.json").bufferedReader().use { it.readText() }
                createResponse(chain, responseString, 200) // Mengembalikan respons sukses
            }
            // Anda bisa menambahkan logika lain untuk endpoint yang berbeda, misalnya:
            // uri.path.contains("approvals") -> {
            //     responseString = context.assets.open("approvals.json").bufferedReader().use { it.readText() }
            //     createResponse(chain, responseString, 200)
            // }
            else -> chain.proceed(chain.request()) // Jika tidak ada mock, lanjutkan request asli
        }
    }

    private fun createResponse(chain: Interceptor.Chain, responseString: String, code: Int): Response {
        return Response.Builder()
            .code(code)
            .message(responseString)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_1)
            .body(responseString.toResponseBody("application/json".toMediaTypeOrNull()))
            .addHeader("content-type", "application/json")
            .build()
    }
}
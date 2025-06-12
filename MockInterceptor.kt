package com.example.dashboard_ewaste_android.data.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MockInterceptor @Inject constructor(
    @ApplicationContext private val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url.toUri()
        val responseString: String

        return when {
            uri.path.contains("dropboxes") -> {
                responseString = context.assets.open("dropboxes.json").bufferedReader().use { it.readText() }
                createResponse(chain, responseString, 200)
            }
            else -> chain.proceed(chain.request())
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
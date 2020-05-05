package com.stardeux.upprime.network

import com.stardeux.upprime.api.ApiConst
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val finalRequest = chain.request().newBuilder()
            .addHeader("x-rapidapi-host", ApiConst.API_HOST)
            .addHeader("x-rapidapi-key", ApiConst.API_KEY)
            .build()

        return chain.proceed(finalRequest)
    }
}
package com.stardeux.upprime.network.amazon

import okhttp3.Interceptor
import okhttp3.Response

class AmazonAuthenticatorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val finalRequest = chain.request().newBuilder()
            .addHeader("x-rapidapi-host", AmazonApiConst.API_HOST)
            .addHeader("x-rapidapi-key", AmazonApiConst.API_KEY)
            .build()

        return chain.proceed(finalRequest)
    }
}
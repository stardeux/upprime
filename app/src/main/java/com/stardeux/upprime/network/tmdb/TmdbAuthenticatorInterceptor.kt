package com.stardeux.upprime.network.tmdb

import okhttp3.Interceptor
import okhttp3.Response

class TmdbAuthenticatorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val finalRequest = chain.request().newBuilder()
            .addHeader("api_key", TmdbApiConst.API_KEY)
            .build()

        return chain.proceed(finalRequest)
    }

}
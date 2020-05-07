package com.stardeux.upprime.network.tmdb

import okhttp3.Interceptor
import okhttp3.Response

class TmdbAuthenticatorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder().addQueryParameter("api_key", TmdbApiConst.API_KEY).build()

        val finalRequest = chain.request().newBuilder()
            .addHeader("api_key", TmdbApiConst.API_KEY)
            .url(url)
            .build()

        return chain.proceed(finalRequest)
    }

}
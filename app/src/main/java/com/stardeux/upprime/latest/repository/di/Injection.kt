package com.stardeux.upprime.latest.repository.di

import com.stardeux.upprime.latest.repository.api.LatestApi
import org.koin.dsl.module
import retrofit2.Retrofit

val latestModule = module { }


fun provideLatestApi(retrofit: Retrofit): LatestApi {
    return retrofit.create(LatestApi::class.java)
}
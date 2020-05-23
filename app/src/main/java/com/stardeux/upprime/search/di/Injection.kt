package com.stardeux.upprime.search.di

import com.stardeux.upprime.network.amazon.di.AMAZON_NAMED_QUALIFIER
import com.stardeux.upprime.search.repository.AmazonSearchRepository
import com.stardeux.upprime.search.repository.api.AmazonSearchApi
import com.stardeux.upprime.search.repository.model.AmazonSearchMediaMapper
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val searchModule = module {
    factory { provideAmazonSearchApi(get(named(AMAZON_NAMED_QUALIFIER))) }
    factory { provideAmazonSearchMediaMapper() }
    factory { provideAmazonSearchRepository(get(), get()) }
}

private fun provideAmazonSearchApi(retrofit: Retrofit): AmazonSearchApi {
    return retrofit.create(AmazonSearchApi::class.java)
}

private fun provideAmazonSearchMediaMapper(): AmazonSearchMediaMapper {
    return AmazonSearchMediaMapper()
}

private fun provideAmazonSearchRepository(
    amazonSearchApi: AmazonSearchApi, amazonSearchMediaMapper: AmazonSearchMediaMapper
): AmazonSearchRepository {
    return AmazonSearchRepository(amazonSearchApi, amazonSearchMediaMapper)
}
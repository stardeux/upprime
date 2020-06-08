package com.stardeux.upprime.search.di

import com.stardeux.upprime.core.analytics.AnalyticsWrapper
import com.stardeux.upprime.country.di.getUserScope
import com.stardeux.upprime.country.usecase.model.AvailableCountry
import com.stardeux.upprime.network.amazon.di.AMAZON_NAMED_QUALIFIER
import com.stardeux.upprime.search.repository.AmazonSearchRepository
import com.stardeux.upprime.search.repository.api.AmazonSearchApi
import com.stardeux.upprime.search.repository.model.AmazonSearchMediaMapper
import com.stardeux.upprime.search.ui.SearchViewModel
import com.stardeux.upprime.search.ui.model.AmazonSearchResultUiMapper
import com.stardeux.upprime.search.usecase.AmazonSearchUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val searchModule = module {
    factory { provideAmazonSearchApi(get(named(AMAZON_NAMED_QUALIFIER))) }
    factory { provideAmazonSearchMediaMapper() }
    factory { provideAmazonSearchRepository(get(), get()) }
    factory { provideAmazonSearchResultUiMapper() }
    viewModel { provideSearchViewModel(getUserScope().get(), get(), get()) }

    scope<AvailableCountry> {
        factory { provideAmazonSearchUseCase(get(), get()) }
    }

}

private fun provideAmazonSearchResultUiMapper(): AmazonSearchResultUiMapper {
    return AmazonSearchResultUiMapper()
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

private fun provideAmazonSearchUseCase(
    amazonSearchRepository: AmazonSearchRepository, availableCountry: AvailableCountry
): AmazonSearchUseCase {
    return AmazonSearchUseCase(amazonSearchRepository, availableCountry)
}

private fun provideSearchViewModel(
    amazonSearchUseCase: AmazonSearchUseCase,
    amazonSearchResultUiMapper: AmazonSearchResultUiMapper,
    analyticsWrapper: AnalyticsWrapper
): SearchViewModel {
    return SearchViewModel(amazonSearchUseCase, amazonSearchResultUiMapper, analyticsWrapper)
}
package com.stardeux.upprime.tmdb.credit.ui

import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.tmdb.credit.ui.model.CreditsUi
import com.stardeux.upprime.tmdb.credit.ui.model.CreditUiMapper
import com.stardeux.upprime.tmdb.credit.usecase.MovieCreditUseCase
import com.stardeux.upprime.tmdb.credit.usecase.SeriesCreditsUseCase
import org.koin.java.KoinJavaComponent.getKoin

class CreditUseCaseFacade(private val creditUiMapper: CreditUiMapper) {

    suspend fun getCredits(mediaType: MediaType, tmdbId: TmdbId): CreditsUi {
        return when (mediaType) {
            MediaType.MOVIE -> getMovieCredits(tmdbId)
            MediaType.SERIES -> getSeriesCredits(tmdbId)
        }
    }

    private suspend fun getSeriesCredits(tmdbId: TmdbId): CreditsUi {
        val seriesCreditsUseCase: SeriesCreditsUseCase = getKoin().get()
        return creditUiMapper.mapToCreditUiList(seriesCreditsUseCase.getMediaCredit(tmdbId))
    }

    private suspend fun getMovieCredits(tmdbId: TmdbId): CreditsUi {
        val movieCreditUseCase: MovieCreditUseCase = getKoin().get()
        return creditUiMapper.mapToCreditUiList(movieCreditUseCase.getMediaCredit(tmdbId))
    }

}
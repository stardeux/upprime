package com.stardeux.upprime.tmdb.credit.ui

import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.tmdb.credit.ui.model.CreditUi
import com.stardeux.upprime.tmdb.credit.ui.model.CreditUiMapper
import com.stardeux.upprime.tmdb.credit.usecase.MovieCreditUseCase
import com.stardeux.upprime.tmdb.credit.usecase.SeriesCreditsUseCase
import org.koin.java.KoinJavaComponent.getKoin

class MediaCreditUseCaseFacade(private val creditUiMapper: CreditUiMapper) {

    suspend fun getCredits(mediaType: MediaType, tmdbId: TmdbId): List<CreditUi> {
        return when (mediaType) {
            MediaType.MOVIE -> getMovieCredits(tmdbId)
            MediaType.SERIES -> getSeriesCredits(tmdbId)
        }
    }

    private suspend fun getSeriesCredits(tmdbId: TmdbId): List<CreditUi> {
        val movieCreditUseCase: MovieCreditUseCase = getKoin().get()
        return creditUiMapper.mapToCreditUiList(movieCreditUseCase.getMediaCredit(tmdbId))
    }

    private suspend fun getMovieCredits(tmdbId: TmdbId): List<CreditUi> {
        val seriesCreditsUseCase: SeriesCreditsUseCase = getKoin().get()
        return creditUiMapper.mapToCreditUiList(seriesCreditsUseCase.getMediaCredit(tmdbId))
    }

}
package com.stardeux.upprime.media.common.usecase

import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.media.fiche.ui.model.MediaFicheUi
import com.stardeux.upprime.media.fiche.ui.model.MediaFicheUiMapper
import com.stardeux.upprime.tmdb.common.request.ImdbMediaRequest
import com.stardeux.upprime.tmdb.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase
import org.koin.java.KoinJavaComponent.getKoin

class GetImdbMediaDetailsUseCaseFacade(private val mediaFicheUiMapper: MediaFicheUiMapper) {

    suspend fun getDetails(mediaType: MediaType, imdbMediaRequest: ImdbMediaRequest): MediaFicheUi {
        return when (mediaType) {
            MediaType.MOVIE -> loadMovieDetail(imdbMediaRequest)
            MediaType.SERIES -> loadSeriesDetail(imdbMediaRequest)
        }
    }

    private suspend fun loadSeriesDetail(imdbMediaRequest: ImdbMediaRequest): MediaFicheUi {
        val getImdbSeriesDetailsUseCase: GetImdbSeriesDetailsUseCase = getKoin().get()
        return mediaFicheUiMapper.mapToMediaFicheUi(
            getImdbSeriesDetailsUseCase(imdbMediaRequest)
        )
    }

    private suspend fun loadMovieDetail(imdbMediaRequest: ImdbMediaRequest): MediaFicheUi {
        val getImdbMovieDetailsUseCase: GetImdbMovieDetailsUseCase = getKoin().get()
        return mediaFicheUiMapper.mapToMediaFicheUi(
            getImdbMovieDetailsUseCase(imdbMediaRequest)
        )
    }
}
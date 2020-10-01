package com.stardeux.upprime.media.common.ui

import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.media.fiche.ui.model.MediaFicheUi
import com.stardeux.upprime.media.fiche.ui.mapper.MediaFicheUiMapper
import com.stardeux.upprime.tmdb.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase
import com.stardeux.upprime.tmdbinapp.mapper.ImdbMediaRequestMapper
import org.koin.java.KoinJavaComponent.getKoin

class GetImdbMediaDetailsUseCaseFacade(
    private val imdbMediaRequestMapper: ImdbMediaRequestMapper,
    private val mediaFicheUiMapper: MediaFicheUiMapper
) {

    suspend fun getDetails(shortMedia: ShortMedia): MediaFicheUi {
        return when (shortMedia.type) {
            MediaType.MOVIE -> loadMovieDetail(shortMedia)
            MediaType.SERIES -> loadSeriesDetail(shortMedia)
        }
    }

    private suspend fun loadSeriesDetail(shortMedia: ShortMedia): MediaFicheUi {
        val getImdbSeriesDetailsUseCase: GetImdbSeriesDetailsUseCase = getKoin().get()
        return mediaFicheUiMapper.mapToMediaFicheUi(
            getImdbSeriesDetailsUseCase(imdbMediaRequestMapper.mapToImdbMediaRequest(shortMedia))
        )
    }

    private suspend fun loadMovieDetail(shortMedia: ShortMedia): MediaFicheUi {
        val getImdbMovieDetailsUseCase: GetImdbMovieDetailsUseCase = getKoin().get()
        return mediaFicheUiMapper.mapToMediaFicheUi(
            getImdbMovieDetailsUseCase(imdbMediaRequestMapper.mapToImdbMediaRequest(shortMedia))
        )
    }
}
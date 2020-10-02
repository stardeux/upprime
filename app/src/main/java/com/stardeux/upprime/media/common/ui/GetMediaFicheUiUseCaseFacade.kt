package com.stardeux.upprime.media.common.ui

import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.media.fiche.ui.model.MediaFicheUi
import com.stardeux.upprime.media.fiche.ui.mapper.MediaFicheUiMapper
import com.stardeux.upprime.tmdb.movie.usecase.GetTmdbMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetTmdbSeriesDetailsUseCase
import com.stardeux.upprime.tmdbinapp.mapper.ImdbMediaRequestMapper
import org.koin.java.KoinJavaComponent.getKoin

class GetMediaFicheUiUseCaseFacade(
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
        val getTmdbSeriesDetailsUseCase: GetTmdbSeriesDetailsUseCase = getKoin().get()
        return mediaFicheUiMapper.mapToMediaFicheUi(
            shortMedia,
            getTmdbSeriesDetailsUseCase(imdbMediaRequestMapper.mapToImdbMediaRequest(shortMedia))
        )
    }

    private suspend fun loadMovieDetail(shortMedia: ShortMedia): MediaFicheUi {
        val getTmdbMovieDetailsUseCase: GetTmdbMovieDetailsUseCase = getKoin().get()
        return mediaFicheUiMapper.mapToMediaFicheUi(
            shortMedia,
            getTmdbMovieDetailsUseCase(imdbMediaRequestMapper.mapToImdbMediaRequest(shortMedia))
        )
    }
}
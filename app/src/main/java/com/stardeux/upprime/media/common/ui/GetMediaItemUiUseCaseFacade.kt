package com.stardeux.upprime.media.common.ui

import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.media.common.ui.model.MediaItemUiMapper
import com.stardeux.upprime.media.common.ui.model.MediaItemUi
import com.stardeux.upprime.tmdb.movie.usecase.GetTmdbMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase
import com.stardeux.upprime.tmdbinapp.mapper.ImdbMediaRequestMapper
import org.koin.java.KoinJavaComponent.getKoin

class GetMediaItemUiUseCaseFacade(
    private val imdbMediaRequestMapper: ImdbMediaRequestMapper,
    private val mediaItemUiMapper: MediaItemUiMapper,
) {

    suspend fun getDetails(shortMedia: ShortMedia, onFullCardClicked: (MediaItemUi) -> Unit): MediaItemUi {
        return when (shortMedia.type) {
            MediaType.MOVIE -> loadMovieDetail(shortMedia, onFullCardClicked)
            MediaType.SERIES -> loadSeriesDetail(shortMedia, onFullCardClicked)
        }
    }

    private suspend fun loadSeriesDetail(shortMedia: ShortMedia, onFullCardClicked: (MediaItemUi) -> Unit): MediaItemUi {
        val getImdbSeriesDetailsUseCase: GetImdbSeriesDetailsUseCase = getKoin().get()
        return mediaItemUiMapper.mapToMediaUi(
            shortMedia,
            getImdbSeriesDetailsUseCase(imdbMediaRequestMapper.mapToImdbMediaRequest(shortMedia)),
            onFullCardClicked
        )
    }

    private suspend fun loadMovieDetail(shortMedia: ShortMedia, onFullCardClicked: (MediaItemUi) -> Unit): MediaItemUi {
        val getTmdbMovieDetailsUseCase: GetTmdbMovieDetailsUseCase = getKoin().get()
        return mediaItemUiMapper.mapToMediaUi(
            shortMedia,
            getTmdbMovieDetailsUseCase(imdbMediaRequestMapper.mapToImdbMediaRequest(shortMedia)),
            onFullCardClicked
        )
    }
}
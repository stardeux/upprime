package com.stardeux.upprime.media.common.usecase

import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.media.fiche.ui.model.MediaFicheUi
import com.stardeux.upprime.media.fiche.ui.model.mapToMediaFicheUi
import com.stardeux.upprime.tmdb.common.request.ImdbMediaRequest
import com.stardeux.upprime.tmdb.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase
import org.koin.java.KoinJavaComponent

class GetImdbMediaDetailsUseCase {

    suspend fun getDetails(mediaType: MediaType, imdbMediaRequest: ImdbMediaRequest): MediaFicheUi {
        return when (mediaType) {
            MediaType.MOVIE -> loadMovieDetail(imdbMediaRequest)
            MediaType.SERIES -> loadSeriesDetail(imdbMediaRequest)
        }
    }

    private suspend fun loadSeriesDetail(imdbMediaRequest: ImdbMediaRequest): MediaFicheUi {
        val getImdbSeriesDetailsUseCase by KoinJavaComponent.inject(GetImdbSeriesDetailsUseCase::class.java)
        return mapToMediaFicheUi(
            getImdbSeriesDetailsUseCase(imdbMediaRequest)
        )
    }

    private suspend fun loadMovieDetail(imdbMediaRequest: ImdbMediaRequest): MediaFicheUi {
        val getImdbMovieDetailsUseCase by KoinJavaComponent.inject(GetImdbMovieDetailsUseCase::class.java)
        return mapToMediaFicheUi(
            getImdbMovieDetailsUseCase(imdbMediaRequest)
        )
    }
}
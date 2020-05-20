package com.stardeux.upprime.media.fiche.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stardeux.upprime.core.extension.exhaustive
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.media.common.usecase.GetImdbMediaDetailsUseCase
import com.stardeux.upprime.tmdb.common.request.ImdbMediaRequest
import com.stardeux.upprime.tmdb.common.request.mapToImdbMediaRequest
import com.stardeux.upprime.tmdb.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase
import com.stardeux.upprime.tmdb.video.repository.model.Video
import com.stardeux.upprime.tmdb.video.usecase.VideoUseCase
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class MediaFicheViewModel(
    private val getImdbMediaDetailsUseCase: GetImdbMediaDetailsUseCase,
    private val videoUseCase: VideoUseCase
) : ViewModel() {

    private val _mediaItemUi = MutableLiveData<MediaFicheUi>()
    val mediaItemUi : LiveData<MediaFicheUi> = _mediaItemUi

    private val _videos = MutableLiveData<List<Video>>()
    val videos : LiveData<List<Video>> = _videos

    fun load(shortMedia: ShortMedia) {
        viewModelScope.launch {
            val imdbMediaRequest = mapToImdbMediaRequest(shortMedia)

            val mediaDetails = getImdbMediaDetailsUseCase.getDetails(shortMedia.type, imdbMediaRequest)
            _mediaItemUi.value = mediaDetails

            val mediaVideos = videoUseCase.getVideos(shortMedia.type, mediaDetails.tmdbId)
            _videos.value = mediaVideos
        }
    }


}
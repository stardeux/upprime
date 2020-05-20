package com.stardeux.upprime.media.fiche.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.media.common.usecase.GetImdbMediaDetailsUseCase
import com.stardeux.upprime.media.fiche.ui.model.MediaFicheUi
import com.stardeux.upprime.tmdb.common.request.mapToImdbMediaRequest
import com.stardeux.upprime.tmdb.video.usecase.MediaVideo
import com.stardeux.upprime.tmdb.video.usecase.VideoUseCase
import kotlinx.coroutines.launch

class MediaFicheViewModel(
    private val getImdbMediaDetailsUseCase: GetImdbMediaDetailsUseCase,
    private val videoUseCase: VideoUseCase
) : ViewModel() {

    private val _mediaItemUi = MutableLiveData<MediaFicheUi>()
    val mediaItemUi : LiveData<MediaFicheUi> = _mediaItemUi

    private val _videos = MutableLiveData<List<MediaVideo>>()
    val videos : LiveData<List<MediaVideo>> = _videos

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
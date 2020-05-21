package com.stardeux.upprime.media.fiche.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stardeux.upprime.core.ui.SingleLiveEvent
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.media.common.usecase.GetImdbMediaDetailsUseCaseFacade
import com.stardeux.upprime.media.fiche.ui.model.MediaFicheUi
import com.stardeux.upprime.tmdb.video.ui.model.MediaVideoMapper
import com.stardeux.upprime.tmdb.video.ui.model.MediaVideoUi
import com.stardeux.upprime.tmdb.common.request.mapToImdbMediaRequest
import com.stardeux.upprime.tmdb.video.usecase.VideoUseCase
import kotlinx.coroutines.launch

class MediaFicheViewModel(
    private val getImdbMediaDetailsUseCaseFacade: GetImdbMediaDetailsUseCaseFacade,
    private val videoUseCase: VideoUseCase,
    private val mediaVideoMapper: MediaVideoMapper
) : ViewModel() {

    private val _mediaItemUi = MutableLiveData<MediaFicheUi>()
    val mediaItemUi: LiveData<MediaFicheUi> = _mediaItemUi

    private val _videos = MutableLiveData<List<MediaVideoUi>>()
    val videos: LiveData<List<MediaVideoUi>> = _videos

    private val _videoClicked = SingleLiveEvent<MediaVideoUi>()
    val videoClicked : LiveData<MediaVideoUi> = _videoClicked

    fun load(shortMedia: ShortMedia) {
        viewModelScope.launch {
            val imdbMediaRequest = mapToImdbMediaRequest(shortMedia)

            val mediaDetails =
                getImdbMediaDetailsUseCaseFacade.getDetails(shortMedia.type, imdbMediaRequest)
            _mediaItemUi.value = mediaDetails

            val mediaVideos = videoUseCase.getVideos(shortMedia.type, mediaDetails.tmdbId)
            _videos.value = mediaVideos?.map {
                mediaVideoMapper.mapToMediaVideoUi(it, ::onMediaVideoUiClicked)
            }
        }
    }

    private fun onMediaVideoUiClicked(mediaVideoUi: MediaVideoUi) {
        _videoClicked.value = mediaVideoUi
    }

}
package com.stardeux.upprime.media.fiche.ui

import android.util.Log
import androidx.annotation.ColorInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stardeux.upprime.core.ui.SingleLiveEvent
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.media.common.ui.GetImdbMediaDetailsUseCaseFacade
import com.stardeux.upprime.media.fiche.ui.model.MediaFicheUi
import com.stardeux.upprime.tmdb.common.request.mapToImdbMediaRequest
import com.stardeux.upprime.tmdb.credit.ui.CreditUseCaseFacade
import com.stardeux.upprime.tmdb.credit.ui.model.CreditsUi
import com.stardeux.upprime.tmdb.video.ui.model.MediaVideoMapper
import com.stardeux.upprime.tmdb.video.ui.model.MediaVideoUi
import com.stardeux.upprime.tmdb.video.usecase.VideoUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class MediaFicheViewModel(
    private val getImdbMediaDetailsUseCaseFacade: GetImdbMediaDetailsUseCaseFacade,
    private val videoUseCase: VideoUseCase,
    private val creditUseCaseFacade: CreditUseCaseFacade,
    private val mediaVideoMapper: MediaVideoMapper
) : ViewModel() {

    private val _mediaItemUi = MutableLiveData<MediaFicheUi>()
    val mediaItemUi: LiveData<MediaFicheUi> = _mediaItemUi

    private val _videos = MutableLiveData<List<MediaVideoUi>>()
    val videos: LiveData<List<MediaVideoUi>> = _videos

    private val _credits = MutableLiveData<CreditsUi>()
    val credits: LiveData<CreditsUi> = _credits

    private val _videoClicked = SingleLiveEvent<MediaVideoUi>()
    val videoClicked: LiveData<MediaVideoUi> = _videoClicked

    private val _illustration = MutableLiveData<BackdropImage>()
    val illustration: LiveData<BackdropImage> = _illustration

    fun load(shortMedia: ShortMedia) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.e("FicheVM", "error", throwable)
        }

        viewModelScope.launch(exceptionHandler) {
            supervisorScope {
                val imdbMediaRequest = mapToImdbMediaRequest(shortMedia)

                val mediaDetails =
                    getImdbMediaDetailsUseCaseFacade.getDetails(shortMedia.type, imdbMediaRequest)
                _mediaItemUi.value = mediaDetails

                launch {
                    val mediaVideos = videoUseCase.getVideos(shortMedia.type, mediaDetails.tmdbId)
                    _videos.value = mediaVideos?.map {
                        mediaVideoMapper.mapToMediaVideoUi(it, ::onMediaVideoUiClicked)
                    }
                }

                launch {
                    val mediaCredits =
                        creditUseCaseFacade.getCredits(shortMedia.type, mediaDetails.tmdbId)
                    _credits.value = mediaCredits
                }
            }
        }
    }

    private fun onMediaVideoUiClicked(mediaVideoUi: MediaVideoUi) {
        _videoClicked.value = mediaVideoUi
    }

    fun onPaletteIllustrationDone(posterFilePath: String, @ColorInt color: Int) {
        _illustration.postValue(BackdropImage.PosterWithBackgroundColor(posterFilePath, color))
    }

    sealed class BackdropImage {
        class Landscape(val backdropUrl: String) : BackdropImage()
        class PosterWithBackgroundColor(
            val posterFilePath: String, @ColorInt val color: Int
        ) : BackdropImage()
    }

}
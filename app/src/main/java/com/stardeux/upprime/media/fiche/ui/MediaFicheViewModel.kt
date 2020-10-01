package com.stardeux.upprime.media.fiche.ui

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stardeux.upprime.core.analytics.AnalyticsValues
import com.stardeux.upprime.core.analytics.AnalyticsWrapper
import com.stardeux.upprime.core.analytics.getTrackingParameters
import com.stardeux.upprime.core.ui.SingleLiveEvent
import com.stardeux.upprime.core.usecase.IsIntentResolvableUseCase
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.media.common.ui.GetMediaFicheUiUseCaseFacade
import com.stardeux.upprime.media.fiche.ui.model.Illustration
import com.stardeux.upprime.media.fiche.ui.model.MediaFicheUi
import com.stardeux.upprime.media.fiche.usecase.MediaIllustrationUseCase
import com.stardeux.upprime.rate.usecase.RateAppUseCase
import com.stardeux.upprime.tmdb.credit.ui.CreditUseCaseFacade
import com.stardeux.upprime.tmdb.credit.ui.model.CreditsUi
import com.stardeux.upprime.tmdb.video.ui.model.MediaVideoMapper
import com.stardeux.upprime.tmdb.video.ui.model.MediaVideoUi
import com.stardeux.upprime.tmdb.video.usecase.VideoUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class MediaFicheViewModel(
    private val shortMedia: ShortMedia,
    private val getMediaFicheUiUseCaseFacade: GetMediaFicheUiUseCaseFacade,
    private val videoUseCase: VideoUseCase,
    private val creditUseCaseFacade: CreditUseCaseFacade,
    private val mediaVideoMapper: MediaVideoMapper,
    private val mediaIllustrationUseCase: MediaIllustrationUseCase,
    rateAppUseCase: RateAppUseCase,
    private val analyticsWrapper: AnalyticsWrapper,
    private val isIntentResolvableUseCase: IsIntentResolvableUseCase
) : ViewModel() {

    private val _mediaItemUi = MutableLiveData<MediaFicheUi>()
    val mediaItemUi: LiveData<MediaFicheUi> = _mediaItemUi

    private val _videos = MutableLiveData<List<MediaVideoUi>>()
    val videos: LiveData<List<MediaVideoUi>> = _videos

    private val _credits = MutableLiveData<CreditsUi>()
    val credits: LiveData<CreditsUi> = _credits

    private val _events = SingleLiveEvent<Event>()
    val events: LiveData<Event> = _events

    private val _illustration = MutableLiveData<Illustration>()
    val illustration: LiveData<Illustration> = _illustration

    init {
        rateAppUseCase.incrementFavorableAction()
    }

    fun load() {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.e("FicheVM", "error", throwable)
            analyticsWrapper.recordException(throwable)
        }

        viewModelScope.launch(exceptionHandler) {
            supervisorScope {

                val mediaDetails =
                    getMediaFicheUiUseCaseFacade.getDetails(shortMedia)
                _mediaItemUi.value = mediaDetails

                _illustration.value = mediaIllustrationUseCase.getIllustration(mediaDetails)

                launch {
                    val mediaVideos = videoUseCase.getVideos(shortMedia.type, mediaDetails.tmdbId)
                    _videos.value = mediaVideos.map {
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

    fun onShareClicked() {
        analyticsWrapper.logEvent(
            AnalyticsValues.Event.FICHE_SHARE, shortMedia.getTrackingParameters()
        )
        _events.value = Event.ShareClicked(shortMedia)
    }

    fun onFabClicked() {
        analyticsWrapper.logEvent(
            AnalyticsValues.Event.FICHE_PLAY, shortMedia.getTrackingParameters()
        )
        _mediaItemUi.value?.let {

            val intent = Intent(Intent.ACTION_VIEW, it.amazonPlayUri)
            if (isIntentResolvableUseCase.isResolvable(intent)) {
                _events.value = Event.PlayClicked(intent)
            } else {
                //Redirect to web
            }
        }
    }

    private fun onMediaVideoUiClicked(mediaVideoUi: MediaVideoUi) {
        trackVideo(mediaVideoUi)
        _events.value = Event.VideoClicked(mediaVideoUi)
    }

    private fun trackVideo(mediaVideoUi: MediaVideoUi) {
        analyticsWrapper.logEvent(
            AnalyticsValues.Event.FICHE_VIDEO_CLICKED,
            mediaVideoUi.getTrackingParameters()
        )
    }

    fun trackScreen(activity: Activity) {
        analyticsWrapper.setCurrentScreen(activity, AnalyticsValues.Screen.FICHE)
    }

    sealed class Event {
        class VideoClicked(val mediaVideoUi: MediaVideoUi) : Event()
        class ShareClicked(val shortMedia: ShortMedia) : Event()
        class PlayClicked(val intent: Intent) : Event()
    }
}
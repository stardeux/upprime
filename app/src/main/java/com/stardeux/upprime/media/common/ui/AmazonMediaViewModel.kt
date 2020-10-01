package com.stardeux.upprime.media.common.ui

import android.util.Log
import androidx.lifecycle.*
import com.stardeux.upprime.core.analytics.AnalyticsValues
import com.stardeux.upprime.core.analytics.AnalyticsWrapper
import com.stardeux.upprime.core.analytics.getTrackingParameters
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.media.common.repository.model.MediaPage
import com.stardeux.upprime.media.common.ui.model.DateSeparatorUi
import com.stardeux.upprime.media.common.ui.model.MediaItemUi
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.tmdb.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase
import com.stardeux.upprime.core.ui.SingleLiveEvent
import com.stardeux.upprime.media.common.ui.model.MediaItemUiMapper
import com.stardeux.upprime.tmdbinapp.mapper.ImdbMediaRequestMapper
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

abstract class AmazonMediaViewModel(
    private val getImdbMovieDetailsUseCase: GetImdbMovieDetailsUseCase,
    private val getImdbSeriesDetailsUseCase: GetImdbSeriesDetailsUseCase,
    private val mediaItemUiMapper: MediaItemUiMapper,
    private val imdbMediaRequestMapper: ImdbMediaRequestMapper,
    private val analyticsWrapper: AnalyticsWrapper
) : ViewModel() {

    private var page = 1
    private var totalCount = 0

    private val _mediaItems = MutableLiveData<List<MediaItemUi>>()
    val mediaItems: LiveData<List<MediaItemUi>> = _mediaItems

    private val _loadingDataState = MutableLiveData<DataLoading>()
    val loadingDataState: LiveData<DataLoading> = _loadingDataState

    private val _navigationEvent = SingleLiveEvent<NavigationEvent>()
    val navigationEvent: LiveData<NavigationEvent> = _navigationEvent

    private val shortMediaItems = LinkedList<List<ShortMedia>>()

    val datedMediaItems: LiveData<List<Any>> = Transformations.map(_mediaItems, ::groupByDate)

    private var loadDetailsJob: Job? = null

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Log.e("Loading Error", "loading failed", exception)
        analyticsWrapper.recordException(exception)

        if (_mediaItems.value.isNullOrEmpty()) {
            _loadingDataState.value = DataLoading.Error
        }
    }

    fun loadNext() {
        if (totalCount > 0 && _mediaItems.value?.size ?: 0 >= totalCount) {
            return
        }

        if (_mediaItems.value.isNullOrEmpty()) {
            _loadingDataState.value = DataLoading.Loading
        }

        viewModelScope.launch(errorHandler) {
            val result = getAmazonMedia(page)
            page++
            totalCount = result.count

            val mediaUi = result.shortMedia.map { mediaItemUiMapper.mapToMediaUi(it, ::onCardClicked) }
            _mediaItems.value =
                (_mediaItems.value?.toMutableList() ?: mutableListOf()).apply { addAll(mediaUi) }

            _loadingDataState.value = DataLoading.Success

            shortMediaItems.add(result.shortMedia)
            loadNextDetails()
        }
    }


    private fun onCardClicked(mediaItemUi: MediaItemUi) {
        analyticsWrapper.logEvent(AnalyticsValues.Event.MEDIA_ITEM_CLICKED, mediaItemUi.getTrackingParameters())
        _navigationEvent.value = NavigationEvent.MediaDetailsFiche(mediaItemUi)
    }

    private fun loadNextDetails() {
        if (loadDetailsJob?.isActive == true) {
            return
        }

        shortMediaItems.poll()?.let {
            loadDetailsJob = viewModelScope.launch {
                it.forEach { shortMedia ->
                    updateViewFullMedia(shortMedia)
                }
            }.also { it.invokeOnCompletion { loadNextDetails() } }
        }
    }


    private suspend fun updateViewFullMedia(shortMedia: ShortMedia) {
        try {
            val fullMedia = loadFullMedia(shortMedia)

            val currentList = _mediaItems.value?.toMutableList()
            currentList?.set(
                currentList.indexOfFirst { it.shortMedia.imdbId == fullMedia.shortMedia.imdbId }, fullMedia
            )

            _mediaItems.value = currentList

        } catch (exception: Exception) {
            Log.e("Unfound", "ImdbId = ${shortMedia.imdbId}", exception)
        }
    }


    private suspend fun loadFullMedia(shortMedia: ShortMedia): MediaItemUi {
        return when (shortMedia.type) {
            MediaType.MOVIE -> {
                val movieDetails = getImdbMovieDetailsUseCase(imdbMediaRequestMapper.mapToImdbMediaRequest(shortMedia))
                mediaItemUiMapper.mapToMediaUi(shortMedia, movieDetails, ::onCardClicked)
            }
            MediaType.SERIES -> {
                val seriesDetails = getImdbSeriesDetailsUseCase(imdbMediaRequestMapper.mapToImdbMediaRequest(shortMedia))
                mediaItemUiMapper.mapToMediaUi(shortMedia, seriesDetails, ::onCardClicked)
            }
        }
    }

    private fun groupByDate(medias: List<MediaItemUi>): MutableList<Any> {
        val groupedMedia = medias.groupBy { it.amazonReleaseDate }

        return mutableListOf<Any>().apply {
            groupedMedia.keys.forEach {
                add(DateSeparatorUi(it))
                addAll(groupedMedia.getValue(it))
            }
        }
    }

    sealed class NavigationEvent {
        class MediaDetailsFiche(val mediaItemUi: MediaItemUi) : NavigationEvent()
    }

    sealed class DataLoading {
        object Loading : DataLoading()
        object Success : DataLoading()
        object Error : DataLoading()
    }

    abstract suspend fun getAmazonMedia(page: Int): MediaPage
}
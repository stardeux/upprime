package com.stardeux.upprime.media.common.ui

import android.util.Log
import androidx.lifecycle.*
import com.stardeux.upprime.media.common.usecase.model.Media
import com.stardeux.upprime.media.common.usecase.model.MediaPage
import com.stardeux.upprime.media.common.ui.model.DateSeparatorUi
import com.stardeux.upprime.media.common.ui.model.MediaItemUi
import com.stardeux.upprime.media.common.ui.model.mapToMediaUi
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.tmdb.common.request.mapToImdbMediaRequest
import com.stardeux.upprime.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.series.usecase.GetImdbSeriesDetailsUseCase
import fr.stardeux.autosc.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.min

abstract class AmazonMediaViewModel(
    private val getImdbMovieDetailsUseCase: GetImdbMovieDetailsUseCase,
    private val getImdbSeriesDetailsUseCase: GetImdbSeriesDetailsUseCase
) : ViewModel() {

    private var page = 0
    private var totalCount = 0

    private val _mediaItems = MutableLiveData<List<MediaItemUi>>()
    val mediaItems: LiveData<List<MediaItemUi>> = _mediaItems

    private val _loadingDataState = MutableLiveData<DataLoading>()
    val loadingDataState: LiveData<DataLoading> = _loadingDataState

    private val _navigationEvent = SingleLiveEvent<NavigationEvent>()
    val navigationEvent: LiveData<NavigationEvent> = _navigationEvent

    private val shortMediaItems = LinkedList<List<Media>>()

    val datedMediaItems: LiveData<List<Any>> = Transformations.map(_mediaItems) { medias ->
        val groupedMedia = medias.groupBy { it.amazonReleaseDate }

        mutableListOf<Any>().apply {
            groupedMedia.keys.forEach {
                add(DateSeparatorUi(it))
                addAll(groupedMedia.getValue(it))
            }
        }
    }

    fun loadNext() {
        if (totalCount > 0 && _mediaItems.value?.size ?: 0 >= totalCount) {
            return
        }

        if (_mediaItems.value?.isEmpty() == true) {
            _loadingDataState.value = DataLoading.Loading
        }


        val errorHandler = CoroutineExceptionHandler { _, exception ->
            Log.e("Loading Error", "loading failed", exception)
            _loadingDataState.value = DataLoading.Error
        }

        viewModelScope.launch(errorHandler) {
            page++
            val result = getAmazonMedia(page)
            totalCount = result.count

            val mediaUi =
                result.media.map { mapToMediaUi(it, ::onFullCardClicked) }
            _mediaItems.value =
                (_mediaItems.value?.toMutableList() ?: mutableListOf()).apply { addAll(mediaUi) }

            _loadingDataState.value = DataLoading.Success

            shortMediaItems.add(result.media)
            loadNextDetails()
        }
    }


    private fun onFullCardClicked(mediaItemUi: MediaItemUi) {
        _navigationEvent.value = NavigationEvent.MediaDetailsFiche(mediaItemUi)
    }

    private fun loadNextDetails() {
        shortMediaItems.poll()?.let { currentShortMediaItemsList ->
            viewModelScope.launch {
                currentShortMediaItemsList.subList(0, min(2, currentShortMediaItemsList.size))
                    .forEach { shortMedia ->
                        try {
                            val completeMediaUi = when (shortMedia.type) {
                                MediaType.MOVIE -> {
                                    mapToMediaUi(
                                        getImdbMovieDetailsUseCase(mapToImdbMediaRequest(shortMedia)),
                                        ::onFullCardClicked
                                    )
                                }

                                MediaType.SERIES -> mapToMediaUi(
                                    getImdbSeriesDetailsUseCase(mapToImdbMediaRequest(shortMedia)),
                                    ::onFullCardClicked
                                )
                            }

                            val currentList = _mediaItems.value?.toMutableList()
                            currentList?.set(
                                currentList.indexOfFirst { it.imdbId == completeMediaUi.imdbId },
                                completeMediaUi
                            )

                            _mediaItems.value = currentList
                        } catch (exception: Exception) {
                            Log.e("Unfound", "ImdbId = ${shortMedia.imdbId}", exception)
                        }
                    }
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
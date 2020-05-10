package com.stardeux.upprime.amazon.common.ui

import android.util.Log
import androidx.lifecycle.*
import com.stardeux.upprime.amazon.common.usecase.model.Media
import com.stardeux.upprime.amazon.common.usecase.model.MediaPage
import com.stardeux.upprime.amazon.common.view.DateSeparatorUi
import com.stardeux.upprime.amazon.common.view.MediaUi
import com.stardeux.upprime.amazon.common.view.mapToMediaUi
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.tmdb.common.request.mapToImdbMediaRequest
import com.stardeux.upprime.tmdb.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.min

abstract class AmazonMediaViewModel(
    private val getImdbMovieDetailsUseCase: GetImdbMovieDetailsUseCase,
    private val getImdbSeriesDetailsUseCase: GetImdbSeriesDetailsUseCase
) : ViewModel() {


    private val _mediaItems = MutableLiveData<List<MediaUi>>()
    val mediaItems: LiveData<List<MediaUi>> = _mediaItems

    private val _loadingDataState = MutableLiveData<DataLoading>()
    val loadingDataState: LiveData<DataLoading> = _loadingDataState

    val datedMediaItems: LiveData<List<Any>> = Transformations.map(_mediaItems) { medias ->
        val groupedMedia = medias.groupBy { it.amazonReleaseDate }

        val orderedMedia = mutableListOf<Any>()

        groupedMedia.keys.forEach {
            orderedMedia.add(DateSeparatorUi(it))
            orderedMedia.addAll(groupedMedia.getValue(it))
        }

        orderedMedia
    }

    private val shortMediaItems = LinkedList<List<Media>>()

    private var page = 0

    fun loadNext() {
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
            val mediaUi = result.media.map(::mapToMediaUi)
            _mediaItems.value =
                (_mediaItems.value?.toMutableList() ?: mutableListOf()).apply { addAll(mediaUi) }

            _loadingDataState.value = DataLoading.Success

            shortMediaItems.add(result.media)
            loadNextDetails()
        }
    }

    private fun loadNextDetails() {
        shortMediaItems.poll()?.let { currentShortMediaItemsList ->
            viewModelScope.launch {
                currentShortMediaItemsList.subList(0, min(2, currentShortMediaItemsList.size))
                    .forEach { shortMedia ->
                        try {
                            val completeMediaUi = when (shortMedia.type) {
                                MediaType.MOVIE -> mapToMediaUi(
                                    getImdbMovieDetailsUseCase(
                                        mapToImdbMediaRequest(shortMedia)
                                    )
                                )
                                MediaType.SERIES -> mapToMediaUi(
                                    getImdbSeriesDetailsUseCase(
                                        mapToImdbMediaRequest(shortMedia)
                                    )
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

    sealed class DataLoading {
        object Loading : DataLoading()
        object Success : DataLoading()
        object Error : DataLoading()
    }

    abstract suspend fun getAmazonMedia(page: Int): MediaPage
}
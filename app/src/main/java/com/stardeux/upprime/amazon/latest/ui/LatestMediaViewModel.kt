package com.stardeux.upprime.amazon.latest.ui

import android.util.Log
import androidx.lifecycle.*
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.amazon.latest.ui.mapper.mapToMediaUi
import com.stardeux.upprime.amazon.latest.ui.model.DateSeparatorUi
import com.stardeux.upprime.amazon.latest.ui.model.MediaUi
import com.stardeux.upprime.amazon.latest.usecase.GetLatestMediaUseCase
import com.stardeux.upprime.amazon.common.model.domain.Media
import com.stardeux.upprime.tmdb.common.request.mapToImdbMediaRequest
import com.stardeux.upprime.tmdb.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.min

class LatestMediaViewModel(
    private val getLatestMediaUseCase: GetLatestMediaUseCase,
    private val getImdbMovieDetailsUseCase: GetImdbMovieDetailsUseCase,
    private val getImdbSeriesDetailsUseCase: GetImdbSeriesDetailsUseCase
) : ViewModel() {

    private val _mediaItems = MutableLiveData<List<MediaUi>>()
    val mediaItems: LiveData<List<MediaUi>> = _mediaItems

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

    fun load() {
        viewModelScope.launch {
            val result = getLatestMediaUseCase.getLatest()
            val mediaUi = result.media.map(::mapToMediaUi)
            _mediaItems.value = mediaUi

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
}
package com.stardeux.upprime.latest.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.latest.ui.mapper.mapToMediaUi
import com.stardeux.upprime.latest.ui.model.MediaUi
import com.stardeux.upprime.latest.usecase.GetLatestUseCase
import com.stardeux.upprime.tmdb.movie.usecase.GetMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetSeriesDetailsUseCase
import kotlinx.coroutines.launch
import java.util.*

class LatestMediaViewModel(
    private val getLatestUseCase: GetLatestUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getSeriesDetailsUseCase: GetSeriesDetailsUseCase
) : ViewModel() {

    private val _mediaItems = MutableLiveData<List<MediaUi>>()
    val mediaItems: LiveData<List<MediaUi>> = _mediaItems

    private val shortMediaItems = LinkedList<List<MediaUi>>()

    fun load() {
        viewModelScope.launch {
            val result = getLatestUseCase.getLatest()
            val mediaUi = result.media.map(::mapToMediaUi)
            _mediaItems.value = mediaUi

            shortMediaItems.add(mediaUi)
            loadNextDetails()
        }
    }

    private fun loadNextDetails() {
        shortMediaItems.poll()?.let { currentShortMediaItemsList ->
            viewModelScope.launch {
                currentShortMediaItemsList.subList(0, 5).forEach { shortMediaUi ->
                    try {
                        val completeMediaUi = when(shortMediaUi.type) {
                            MediaType.MOVIE -> mapToMediaUi(getMovieDetailsUseCase(shortMediaUi.imdbId), shortMediaUi)
                            MediaType.SERIES -> mapToMediaUi(getSeriesDetailsUseCase(shortMediaUi.imdbId), shortMediaUi)
                        }

                        val currentList = _mediaItems.value?.toMutableList()
                        currentList?.set(
                            currentList.indexOfFirst { it.imdbId == completeMediaUi.imdbId },
                            completeMediaUi
                        )

                        _mediaItems.value = currentList
                    } catch (exception: Exception) {
                        Log.e("GetMovieDetails", "ImdbId = ${shortMediaUi.imdbId}", exception)
                    }
                }
            }
        }
    }
}
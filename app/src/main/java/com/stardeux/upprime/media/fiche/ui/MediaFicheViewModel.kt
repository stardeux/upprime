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
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class MediaFicheViewModel(private val getImdbMediaDetailsUseCase: GetImdbMediaDetailsUseCase) : ViewModel() {

    private val _mediaItemUi = MutableLiveData<MediaFicheUi>()
    val mediaItemUi : LiveData<MediaFicheUi> = _mediaItemUi

    fun load(shortMedia: ShortMedia) {
        viewModelScope.launch {
            val imdbMediaRequest = mapToImdbMediaRequest(shortMedia)
            _mediaItemUi.value = getImdbMediaDetailsUseCase.getDetails(shortMedia.type, imdbMediaRequest)
        }
    }


}
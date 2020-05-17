package com.stardeux.upprime.media.fiche.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.tmdb.common.request.ImdbMediaRequest
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class MediaFicheViewModel : ViewModel() {

    private val _mediaItemUi = MutableLiveData<MediaFicheUi>()
    val mediaItemUi : LiveData<MediaFicheUi> = _mediaItemUi

    fun load(imdbMediaRequest: ImdbMediaRequest, mediaType: MediaType) {
        viewModelScope.launch {

        }
    }


}
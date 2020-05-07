package com.stardeux.upprime.latest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stardeux.upprime.latest.ui.mapper.mapToMediaUi
import com.stardeux.upprime.latest.ui.model.MediaUi
import com.stardeux.upprime.latest.usecase.GetLatestUseCase
import kotlinx.coroutines.launch

class LatestFragmentViewModel(
    private val getLatestUseCase: GetLatestUseCase
) : ViewModel(){

    private val _mediaItems = MutableLiveData<List<MediaUi>>()
    val mediaItems: LiveData<List<MediaUi>> = _mediaItems

    fun load() {
        viewModelScope.launch {
            val result = getLatestUseCase.getLatest()
            _mediaItems.value = result.media.map(::mapToMediaUi)
        }

    }
}
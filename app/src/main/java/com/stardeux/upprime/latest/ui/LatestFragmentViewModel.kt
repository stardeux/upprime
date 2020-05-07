package com.stardeux.upprime.latest.ui

import androidx.lifecycle.MutableLiveData
import com.stardeux.upprime.latest.ui.model.MediaUi
import com.stardeux.upprime.latest.usecase.GetLatestUseCase

class LatestFragmentViewModel (
    private val getLatestUseCase: GetLatestUseCase
){

    private val _mediaItems = MutableLiveData<List<MediaUi>>()

    fun load() {

    }
}
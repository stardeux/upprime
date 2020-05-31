package com.stardeux.upprime.core.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

class UpdateOnActiveLiveData : LiveData<Boolean>() {
    override fun onActive() {
        super.onActive()
        value = true
    }
}

fun <T> updateOnActiveLiveData(doOnActive: () -> T): LiveData<T> {
    return Transformations.map(UpdateOnActiveLiveData()) {
        doOnActive()
    }
}
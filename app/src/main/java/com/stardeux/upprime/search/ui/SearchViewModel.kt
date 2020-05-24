package com.stardeux.upprime.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stardeux.upprime.search.ui.model.MediaTypeFilter
import com.stardeux.upprime.search.ui.model.YearInterval

class SearchViewModel : ViewModel() {

    private val _mediaTypeFilter = MutableLiveData<MediaTypeFilter>().apply {
        value = MediaTypeFilter.ALL
    }
    val mediaTypeFilter: LiveData<MediaTypeFilter> = _mediaTypeFilter

    private val _yearInterval =
        MutableLiveData<YearInterval>().apply { value = YearInterval.defaultYearInterval() }
    val yearInterval: LiveData<YearInterval> = _yearInterval


    fun onQueryTextChanged(queryText: String) {

    }

    fun onMediaTypeFilterChanged() {

    }

    fun onYearStartChanged(yearStart: Int) {

    }

    fun onYearEndChanged(yearEnd: Int) {

    }
}
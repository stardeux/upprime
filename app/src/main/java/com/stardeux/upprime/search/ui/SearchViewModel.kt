package com.stardeux.upprime.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stardeux.upprime.search.ui.model.MediaTypeFilter
import com.stardeux.upprime.search.ui.model.YearIntervalUi

class SearchViewModel : ViewModel() {

    private val _mediaTypeFilter = MutableLiveData<MediaTypeFilter>().apply {
        value = MediaTypeFilter.ALL
    }
    val mediaTypeFilter: LiveData<MediaTypeFilter> = _mediaTypeFilter

    private val _startYearInterval =
        MutableLiveData<YearIntervalUi>().apply { value = YearIntervalUi.defaultYearInterval() }
    val startYearInterval: LiveData<YearIntervalUi> = _startYearInterval

    private val _endYearInterval = MutableLiveData<YearIntervalUi>().apply {
        value = YearIntervalUi.defaultYearInterval()
    }
    private val endYearInterval = _endYearInterval


    fun onQueryTextChanged(queryText: String) {

    }

    fun onMediaTypeFilterChanged() {

    }

    fun onYearStartChanged(yearStart: Int) {

    }

    fun onYearEndChanged(yearEnd: Int) {

    }
}
package com.stardeux.upprime.search.ui

import androidx.lifecycle.*
import com.stardeux.upprime.core.ui.SingleLiveEvent
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.search.repository.model.AmazonSearchRequest
import com.stardeux.upprime.search.ui.model.AmazonSearchResultUi
import com.stardeux.upprime.search.ui.model.AmazonSearchResultUiMapper
import com.stardeux.upprime.search.ui.model.MediaTypeFilter
import com.stardeux.upprime.search.ui.model.YearIntervalUi
import com.stardeux.upprime.search.usecase.AmazonSearchUseCase
import com.stardeux.upprime.search.usecase.model.AmazonSearchResultContainer
import org.koin.ext.scope

class SearchViewModel(
    private val amazonSearchUseCase: AmazonSearchUseCase,
    private val amazonSearchResultUiMapper: AmazonSearchResultUiMapper
) : ViewModel() {

    private val _mediaTypeFilter = MutableLiveData(MediaTypeFilter.ALL)
    val mediaTypeFilter: LiveData<MediaTypeFilter> = _mediaTypeFilter

    private val _startYearInterval = MutableLiveData(YearIntervalUi.defaultYearInterval(1990))
    val startYearInterval: LiveData<YearIntervalUi> = _startYearInterval

    private val _endYearInterval =
        MutableLiveData(YearIntervalUi.defaultYearInterval(YearIntervalUi.MAX_YEAR))
    val endYearInterval = _endYearInterval

    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String> = _searchQuery

    private val _searchResultClicked = SingleLiveEvent<ShortMedia>()
    val searchResultClicked: LiveData<ShortMedia> = _searchResultClicked

    val results = Transformations.switchMap(_searchQuery) { query ->
        liveData {
            val searchResults = search(query)
            emit(searchResults.results.mapNotNull {
                amazonSearchResultUiMapper.mapToAmazonSearchResultUi(it, ::onSearchResultClicked)
            })
        }
    }

    private fun onSearchResultClicked(amazonSearchResultUi: AmazonSearchResultUi) {
        _searchResultClicked.value =
            amazonSearchResultUiMapper.mapToShortMedia(amazonSearchResultUi)
    }

    fun onYearStartChanged(yearStart: Int) {
        _startYearInterval.value = _startYearInterval.value?.copy(selectedYear = yearStart)
        val selectedEndYear = requireNotNull(_endYearInterval.value?.selectedYear)
        if (yearStart > selectedEndYear) {
            _endYearInterval.value = _endYearInterval.value?.copy(selectedYear = yearStart)
        }
    }

    fun onYearEndChanged(yearEnd: Int) {
        _endYearInterval.value = _endYearInterval.value?.copy(selectedYear = yearEnd)
        val selectedStartYear = requireNotNull(_startYearInterval.value?.selectedYear)
        if (yearEnd < selectedStartYear) {
            _startYearInterval.value = _startYearInterval.value?.copy(selectedYear = yearEnd)
        }
    }

    fun onQueryTextChanged(queryText: String) {
        _searchQuery.value = queryText
    }

    suspend fun search(query: String): AmazonSearchResultContainer {
        return amazonSearchUseCase.search(buildRequest(query))
    }

    private fun buildRequest(query: String): AmazonSearchRequest {
        val yearStart = requireNotNull(_startYearInterval.value?.selectedYear)
        val yearEnd = requireNotNull(_endYearInterval.value?.selectedYear)

        return AmazonSearchRequest(query, yearStart, yearEnd, 1)
    }

    fun onMediaTypeFilterChanged(mediaTypeFilter: MediaTypeFilter) {
        _mediaTypeFilter.value = mediaTypeFilter
    }

}
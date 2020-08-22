package com.stardeux.upprime.search.ui

import android.app.Activity
import android.util.Log
import androidx.core.os.bundleOf
import androidx.lifecycle.*
import com.stardeux.upprime.core.analytics.AnalyticsValues
import com.stardeux.upprime.core.analytics.AnalyticsWrapper
import com.stardeux.upprime.core.analytics.getTrackingValue
import com.stardeux.upprime.core.ui.SingleLiveEvent
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.media.common.ui.AmazonMediaViewModel
import com.stardeux.upprime.search.repository.model.AmazonSearchRequest
import com.stardeux.upprime.search.ui.model.AmazonSearchResultUi
import com.stardeux.upprime.search.ui.model.AmazonSearchResultUiMapper
import com.stardeux.upprime.search.ui.model.MediaTypeFilter
import com.stardeux.upprime.search.ui.model.YearIntervalUi
import com.stardeux.upprime.search.usecase.AmazonSearchUseCase
import com.stardeux.upprime.search.usecase.model.AmazonSearchResultContainer
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchViewModel(
    private val amazonSearchUseCase: AmazonSearchUseCase,
    private val amazonSearchResultUiMapper: AmazonSearchResultUiMapper,
    private val analyticsWrapper: AnalyticsWrapper
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

    private val _results = MutableLiveData<ViewState>()
    val results: LiveData<ViewState> = _results

    private var queryJob: Job? = null

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Log.e("Loading Error", "loading failed", exception)
        analyticsWrapper.recordException(exception)

        if ((_results.value as? ViewState.Results)?.result?.isEmpty() != false) {
            _results.value = ViewState.Error
        }
    }

    fun retry() {
        _searchQuery.value?.let {
            _results.value = ViewState.Loading
            queryJob = viewModelScope.launch(errorHandler) {
                _results.value = ViewState.Results(it, search(it))
            }
        }
    }

    suspend fun search(query: String): List<AmazonSearchResultUi> {
        val request = buildRequest(query).also { trackQuery(it) }
        val searchResults = amazonSearchUseCase.search(request)

        return searchResults.results.mapNotNull {
            amazonSearchResultUiMapper.mapToAmazonSearchResultUi(
                it, ::onSearchResultClicked
            )
        }
    }

    fun onQueryTextChanged(queryText: String) {
        if (_searchQuery.value == queryText) {
            return
        }

        _searchQuery.value = queryText
        queryJob?.cancel()
        if (queryText.isEmpty()) {
            _results.value = ViewState.Criteria
        } else {
            _results.value = ViewState.Loading
            queryJob = viewModelScope.launch(errorHandler) {
                delay(1000)
                _results.value = ViewState.Results(queryText, search(queryText))
            }
        }
    }

    private fun onSearchResultClicked(amazonSearchResultUi: AmazonSearchResultUi) {
        analyticsWrapper.logEvent(AnalyticsValues.Event.SEARCH_RESULTS_CLICKED)
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

    private fun trackQuery(amazonSearchRequest: AmazonSearchRequest) {
        with(amazonSearchRequest) {
            analyticsWrapper.logEvent(
                AnalyticsValues.Event.SEARCH_QUERY, bundleOf(
                    AnalyticsValues.Params.SEARCH_EVENT_QUERY to title,
                    AnalyticsValues.Params.SEARCH_EVENT_MEDIA_TYPE to mediaTypeFilter.getTrackingValue(),
                    AnalyticsValues.Params.SEARCH_EVENT_YEAR_START to yearStart,
                    AnalyticsValues.Params.SEARCH_EVENT_YEAR_END to yearEnd
                )
            )
        }
    }

    private fun buildRequest(query: String): AmazonSearchRequest {
        val yearStart = requireNotNull(_startYearInterval.value?.selectedYear)
        val yearEnd = requireNotNull(_endYearInterval.value?.selectedYear)
        val mediaTypeFilter = requireNotNull(_mediaTypeFilter.value)

        return AmazonSearchRequest(query, mediaTypeFilter, yearStart, yearEnd, 1)
    }

    fun onMediaTypeFilterChanged(mediaTypeFilter: MediaTypeFilter) {
        _mediaTypeFilter.value = mediaTypeFilter
    }

    fun trackScreen(activity: Activity) {
        analyticsWrapper.setCurrentScreen(activity, AnalyticsValues.Screen.SEARCH)
    }

    sealed class ViewState {
        object Criteria : ViewState()
        object Loading : ViewState()
        object Error : ViewState()
        class Results(val query: String, val result: List<AmazonSearchResultUi>) : ViewState()
    }
}
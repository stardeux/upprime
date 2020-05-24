package com.stardeux.upprime.search.ui

import android.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.NumberPicker
import android.widget.SeekBar
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.colorDivider
import com.stardeux.upprime.core.extension.observeNotNull
import com.stardeux.upprime.core.extension.onValueChanged
import com.stardeux.upprime.search.ui.model.MediaTypeFilter
import com.stardeux.upprime.search.ui.model.YearIntervalUi
import com.stardeux.upprime.search.usecase.model.AmazonSearchResultContainer
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val searchViewModel: SearchViewModel by lazy { requireActivity().getViewModel<SearchViewModel>() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startYearPicker.colorDivider(Color.TRANSPARENT)
        endYearPicker.colorDivider(Color.TRANSPARENT)

        with(searchViewModel) {
            searchQuery.observeNotNull(viewLifecycleOwner, ::handleQuery)
            results.observeNotNull(viewLifecycleOwner, ::handleSearchResults)
            mediaTypeFilter.observeNotNull(viewLifecycleOwner, ::handleMediaTypeFilter)
            startYearInterval.observeNotNull(viewLifecycleOwner, ::handleStartYearInterval)
            endYearInterval.observeNotNull(viewLifecycleOwner, ::handleEndYearInterval)
            mediaTypeFiltersGroup.setOnCheckedChangeListener { _, checkedId ->
                onMediaTypeFilterChanged(mapRadioButtonIdToFilter(checkedId))
            }
        }
    }

    private fun handleQuery(query: String) {
        val isQueryExists = query.isNotBlank()
        searchResultRecycler.isVisible = isQueryExists
        searchCriteriaGroup.isVisible = !isQueryExists
    }

    private fun handleStartYearInterval(yearIntervalUi: YearIntervalUi) {
        handleYearInterval(startYearPicker, yearIntervalUi)
    }

    private fun handleEndYearInterval(yearIntervalUi: YearIntervalUi) {
        handleYearInterval(endYearPicker, yearIntervalUi)
    }

    private fun handleYearInterval(numberPicker: NumberPicker, yearIntervalUi: YearIntervalUi) {
        with(numberPicker) {
            minValue = yearIntervalUi.yearStart
            maxValue = yearIntervalUi.yearEnd
            value = yearIntervalUi.selectedYear
        }
    }


    private fun handleSearchResults(amazonSearchResultContainer: AmazonSearchResultContainer) {

    }

    private fun mapRadioButtonIdToFilter(@IdRes radioButtonId: Int): MediaTypeFilter {
        return when (radioButtonId) {
            R.id.allMediaRadioButton -> MediaTypeFilter.ALL
            R.id.movieRadioButton -> MediaTypeFilter.MOVIE
            R.id.seriesRadioButton -> MediaTypeFilter.SERIES
            else -> throw IllegalStateException("unknown radio button id")
        }
    }

    private fun handleMediaTypeFilter(mediaTypeFilter: MediaTypeFilter) {
        when (mediaTypeFilter) {
            MediaTypeFilter.ALL -> allMediaRadioButton.isChecked = true
            MediaTypeFilter.MOVIE -> movieRadioButton.isChecked = true
            MediaTypeFilter.SERIES -> seriesRadioButton.isChecked = true
        }
    }

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}
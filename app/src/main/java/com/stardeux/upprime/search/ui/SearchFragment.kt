package com.stardeux.upprime.search.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shawnlin.numberpicker.NumberPicker
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.observeNotNull
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.media.fiche.ui.MediaFicheActivity
import com.stardeux.upprime.search.ui.list.SearchResultAdapter
import com.stardeux.upprime.search.ui.model.AmazonSearchResultUi
import com.stardeux.upprime.search.ui.model.MediaTypeFilter
import com.stardeux.upprime.search.ui.model.YearIntervalUi
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val searchViewModel: SearchViewModel by lazy { requireActivity().getViewModel<SearchViewModel>() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(searchResultRecycler) {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = SearchResultAdapter()
        }

        initNumberPicker(startYearPicker) { _, _, newVal ->
            searchViewModel.onYearStartChanged(
                newVal
            )
        }
        initNumberPicker(endYearPicker) { _, _, newVal -> searchViewModel.onYearEndChanged(newVal) }

        mediaTypeFiltersGroup.setOnCheckedChangeListener { _, checkedId ->
            searchViewModel.onMediaTypeFilterChanged(mapRadioButtonIdToFilter(checkedId))
        }

        with(searchViewModel) {
            searchQuery.observeNotNull(viewLifecycleOwner, ::handleQuery)
            results.observeNotNull(viewLifecycleOwner, ::handleSearchResults)
            mediaTypeFilter.observeNotNull(viewLifecycleOwner, ::handleMediaTypeFilter)
            startYearInterval.observeNotNull(viewLifecycleOwner, ::handleStartYearInterval)
            endYearInterval.observeNotNull(viewLifecycleOwner, ::handleEndYearInterval)
            searchResultClicked.observeNotNull(viewLifecycleOwner, ::handleSearchResultClicked)
        }
    }

    private fun initNumberPicker(
        numberPicker: NumberPicker,
        onValueChangeListener: (NumberPicker, Int, Int) -> Unit
    ) {
        with(numberPicker) {
            formatter = NumberPicker.Formatter { number -> number.toString() }
            setOnValueChangedListener(onValueChangeListener)
        }
    }

    private fun handleSearchResultClicked(shortMedia: ShortMedia) {
        startActivity(MediaFicheActivity.newIntent(requireContext(), shortMedia))
    }

    private fun handleQuery(query: String) {
        val isQueryExists = query.isNotBlank()
        searchProgressBar.isVisible = isQueryExists
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


    private fun handleSearchResults(amazonSearchResults: List<AmazonSearchResultUi>) {
        searchProgressBar.isVisible = false
        (searchResultRecycler.adapter as SearchResultAdapter).submitResults(amazonSearchResults)
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
package com.stardeux.upprime.search.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shawnlin.numberpicker.NumberPicker
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.observeNotNull
import com.stardeux.upprime.core.viewbinding.viewBinding
import com.stardeux.upprime.databinding.FragmentSearchBinding
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.media.fiche.ui.MediaFicheActivity
import com.stardeux.upprime.search.ui.list.SearchResultAdapter
import com.stardeux.upprime.search.ui.model.MediaTypeFilter
import com.stardeux.upprime.search.ui.model.YearIntervalUi
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val searchViewModel: SearchViewModel by lazy { requireActivity().getViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel.trackScreen(requireActivity())

        with(binding.searchResultRecycler) {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = SearchResultAdapter()
            val dividerItemDecoration = DividerItemDecoration(
                context, RecyclerView.VERTICAL
            )
            addItemDecoration(dividerItemDecoration)
        }

        initNumberPicker(binding.startYearPicker) { _, _, newVal ->
            searchViewModel.onYearStartChanged(newVal)
        }
        initNumberPicker(binding.endYearPicker) { _, _, newVal -> searchViewModel.onYearEndChanged(newVal) }

        binding.mediaTypeFiltersGroup.setOnCheckedChangeListener { _, checkedId ->
            searchViewModel.onMediaTypeFilterChanged(mapRadioButtonIdToFilter(checkedId))
        }

        binding.searchErrorView.setOnRetryClicked {
            searchViewModel.retry()
        }

        with(searchViewModel) {
            results.observeNotNull(viewLifecycleOwner, ::handleSearchResults)
            mediaTypeFilter.observeNotNull(viewLifecycleOwner, ::handleMediaTypeFilter)
            startYearInterval.observeNotNull(viewLifecycleOwner, ::handleStartYearInterval)
            endYearInterval.observeNotNull(viewLifecycleOwner, ::handleEndYearInterval)
            searchResultClicked.observeNotNull(viewLifecycleOwner, ::handleSearchResultClicked)
        }
    }

    private fun initNumberPicker(
        numberPicker: NumberPicker, onValueChangeListener: (NumberPicker, Int, Int) -> Unit
    ) {
        with(numberPicker) {
            formatter = NumberPicker.Formatter { number -> number.toString() }
            setOnValueChangedListener(onValueChangeListener)
        }
    }

    private fun handleSearchResultClicked(shortMedia: ShortMedia) {
        startActivity(MediaFicheActivity.newIntent(requireContext(), shortMedia))
    }

    private fun handleStartYearInterval(yearIntervalUi: YearIntervalUi) {
        handleYearInterval(binding.startYearPicker, yearIntervalUi)
    }

    private fun handleEndYearInterval(yearIntervalUi: YearIntervalUi) {
        handleYearInterval(binding.endYearPicker, yearIntervalUi)
    }

    private fun handleYearInterval(numberPicker: NumberPicker, yearIntervalUi: YearIntervalUi) {
        with(numberPicker) {
            minValue = yearIntervalUi.yearStart
            maxValue = yearIntervalUi.yearEnd
            value = yearIntervalUi.selectedYear
        }
    }

    private fun handleSearchResults(amazonSearchResults: SearchViewModel.ViewState) {
        binding.searchCriteriaGroup.isVisible = amazonSearchResults is SearchViewModel.ViewState.Criteria
        binding.searchProgressBar.isVisible = amazonSearchResults is SearchViewModel.ViewState.Loading
        binding.searchErrorView.isVisible = amazonSearchResults is SearchViewModel.ViewState.Error
        binding.searchResultRecycler.isVisible = amazonSearchResults is SearchViewModel.ViewState.Results && amazonSearchResults.result.isNotEmpty()
        binding.searchEmptyView.isVisible = amazonSearchResults is SearchViewModel.ViewState.Results && amazonSearchResults.result.isEmpty()

        if (amazonSearchResults is SearchViewModel.ViewState.Results) {
            if (amazonSearchResults.result.isEmpty()) {
                binding.searchEmptyView.setEmptyQuery(amazonSearchResults.query)
            } else {
                (binding.searchResultRecycler.adapter as SearchResultAdapter).submitResults(amazonSearchResults.result)
            }
        }
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
            MediaTypeFilter.ALL -> binding.allMediaRadioButton.isChecked = true
            MediaTypeFilter.MOVIE -> binding.movieRadioButton.isChecked = true
            MediaTypeFilter.SERIES -> binding.seriesRadioButton.isChecked = true
        }
    }

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}
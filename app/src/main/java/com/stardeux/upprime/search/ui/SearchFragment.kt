package com.stardeux.upprime.search.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.colorDivider
import com.stardeux.upprime.core.extension.observeNotNull
import com.stardeux.upprime.core.extension.onValueChanged
import com.stardeux.upprime.search.ui.model.MediaTypeFilter
import com.stardeux.upprime.search.ui.model.YearIntervalUi
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val searchViewModel: SearchViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //startYearIntervalView.setText(R.string.search_filter_year_start)
        //endYearIntervalView.setText(R.string.search_filter_year_end)

        //startYearIntervalView.setOnValueChanged { searchViewModel.onYearStartChanged(it) }
        //endYearIntervalView.setOnValueChanged { searchViewModel.onYearEndChanged(it) }

        startYearPicker.minValue = 1900
        startYearPicker.maxValue = 2020
        startYearPicker.value = 1990
        startYearPicker.colorDivider(Color.TRANSPARENT)

        endYearPicker.minValue = 1900
        endYearPicker.maxValue = 2020
        endYearPicker.value = 2020
        endYearPicker.colorDivider(Color.TRANSPARENT)

        /*
        endNumberPicker.minValue = 1900
        endNumberPicker.maxValue = 2020
        endNumberPicker.value = 2020
*/
        with(searchViewModel) {

            mediaTypeFilter.observeNotNull(viewLifecycleOwner,::handleMediaTypeFilter)


            startYearInterval.observeNotNull(viewLifecycleOwner) {
                //startYearIntervalView.bind(it)
            }

            endYearInterval.observeNotNull(viewLifecycleOwner) {
                //endYearIntervalView.bind(it)
            }
        }
    }

    private fun handleMediaTypeFilter(mediaTypeFilter: MediaTypeFilter) {
        when(mediaTypeFilter) {
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
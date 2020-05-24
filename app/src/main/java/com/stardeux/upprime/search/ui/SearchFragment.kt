package com.stardeux.upprime.search.ui

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.observeNotNull
import com.stardeux.upprime.core.extension.onValueChanged
import com.stardeux.upprime.search.ui.model.YearIntervalUi
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.ext.android.inject

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val searchViewModel: SearchViewModel by inject()

    private var minYear: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startYearIntervalView.setText(R.string.search_filter_year_start)
        endYearIntervalView.setText(R.string.search_filter_year_end)

        with(searchViewModel) {
            startYearIntervalView.setOnValueChanged { value ->
                minYear?.let { onYearStartChanged(it + value) }
            }

            endYearIntervalView.setOnValueChanged { value ->
                minYear?.let { onYearEndChanged(it + value) }
            }

        }
    }


    private fun handleYearInterval(seekBar: SeekBar, yearIntervalUi: YearIntervalUi) {
        minYear = yearIntervalUi.yearStart

        seekBar.max = yearIntervalUi.yearEnd - yearIntervalUi.yearStart
        seekBar.incrementProgressBy(1)
        //yearStartMinText.text = yearIntervalUi.yearStart.toString()
        //yearStartMaxText.text = yearIntervalUi.yearEnd.toString()
    }

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}
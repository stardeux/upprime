package com.stardeux.upprime.search.ui

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.observeNotNull
import com.stardeux.upprime.search.ui.model.YearInterval
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.ext.android.inject

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val searchViewModel : SearchViewModel by inject()

    private var minYear: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        yearStartSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                minYear?.let {
                    searchViewModel.onYearStartChanged(it + progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })


        with(searchViewModel) {
            yearInterval.observeNotNull(viewLifecycleOwner, ::handleYearInterval)
        }

    }
    private fun handleYearInterval(yearInterval: YearInterval) {
        minYear = yearInterval.yearStart

        yearStartSeekbar.max = yearInterval.yearEnd - yearInterval.yearStart
        yearStartSeekbar.incrementProgressBy(1)
        yearStartMinText.text = yearInterval.yearStart.toString()
        yearStartMaxText.text = yearInterval.yearEnd.toString()

        yearEndSeekbar.max = yearInterval.yearEnd - yearInterval.yearStart
        yearEndSeekbar.incrementProgressBy(1)
        yearEndMinText.text = yearInterval.yearStart.toString()
        yearEndMaxText.text = yearInterval.yearEnd.toString()
    }

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}
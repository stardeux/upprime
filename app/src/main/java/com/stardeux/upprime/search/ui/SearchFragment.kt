package com.stardeux.upprime.search.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.applyCommonBack
import kotlinx.android.synthetic.main.activity_media_fiche.*

class SearchFragment : Fragment(R.layout.fragment_search) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}
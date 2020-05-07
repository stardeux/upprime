package com.stardeux.upprime.latest.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.stardeux.upprime.R

class LatestFragment : Fragment(R.layout.fragment_latest) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): LatestFragment {
            return LatestFragment()
        }
    }
}
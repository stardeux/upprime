package com.stardeux.upprime.amazon.expired.ui

import androidx.fragment.app.Fragment
import com.stardeux.upprime.R
import com.stardeux.upprime.amazon.latest.ui.LatestMediaFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExpiredMediaFragment : Fragment(R.layout.fragment_latest) {

    private val expiredViewModel: ExpiredMediaViewModel by viewModel()

    companion object {
        fun newInstance(): LatestMediaFragment {
            return LatestMediaFragment()
        }
    }
}
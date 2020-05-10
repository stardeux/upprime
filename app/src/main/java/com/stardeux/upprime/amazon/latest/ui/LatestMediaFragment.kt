package com.stardeux.upprime.amazon.latest.ui

import com.stardeux.upprime.amazon.common.ui.AmazonMediaFragment
import com.stardeux.upprime.amazon.common.ui.AmazonMediaViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LatestMediaFragment : AmazonMediaFragment() {

    private val latestViewModel: LatestMediaViewModel by viewModel()

    override fun getViewModel(): AmazonMediaViewModel {
        return latestViewModel
    }

    companion object {
        fun newInstance(): LatestMediaFragment {
            return LatestMediaFragment()
        }
    }
}
package com.stardeux.upprime.media.latest.ui

import com.stardeux.upprime.media.common.ui.AmazonMediaFragment
import com.stardeux.upprime.media.common.ui.AmazonMediaViewModel
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
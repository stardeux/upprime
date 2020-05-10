package com.stardeux.upprime.amazon.expired.ui

import com.stardeux.upprime.amazon.common.ui.AmazonMediaFragment
import com.stardeux.upprime.amazon.common.ui.AmazonMediaViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExpiredMediaFragment : AmazonMediaFragment() {

    private val expiredViewModel: ExpiredMediaViewModel by viewModel()

    override fun getViewModel(): AmazonMediaViewModel {
        return expiredViewModel
    }

    companion object {
        fun newInstance(): ExpiredMediaFragment {
            return ExpiredMediaFragment()
        }
    }
}
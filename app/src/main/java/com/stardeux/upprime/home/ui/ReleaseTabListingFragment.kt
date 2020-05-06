package com.stardeux.upprime.home.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.stardeux.upprime.R
import com.stardeux.upprime.core.model.mapToString
import kotlinx.android.synthetic.main.fragment_tab_listing.*

class ReleaseTabListingFragment : Fragment(R.layout.fragment_tab_listing) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pager.adapter = ReleaseTabAdapter(this)

        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = getReleaseTabAdapter().getReleaseType(position).mapToString(requireContext())
        }.attach()
    }

    private fun getReleaseTabAdapter(): ReleaseTabAdapter {
        return pager.adapter as ReleaseTabAdapter
    }

    companion object {
        fun newInstance(): ReleaseTabListingFragment {
            return ReleaseTabListingFragment()
        }
    }
}
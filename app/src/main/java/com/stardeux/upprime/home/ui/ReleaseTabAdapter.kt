package com.stardeux.upprime.home.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.stardeux.upprime.media.expired.ui.ExpiredMediaFragment
import com.stardeux.upprime.core.model.ReleaseType
import com.stardeux.upprime.media.latest.ui.LatestMediaFragment

class ReleaseTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabs = listOf(ReleaseType.NEW, ReleaseType.EXPIRING)

    public fun getReleaseType(position: Int): ReleaseType {
        return tabs[position]
    }

    override fun getItemCount(): Int {
        return tabs.size
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            LatestMediaFragment.newInstance()
        } else {
            ExpiredMediaFragment.newInstance()
        }
    }
}
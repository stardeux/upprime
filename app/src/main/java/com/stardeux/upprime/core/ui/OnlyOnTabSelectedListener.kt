package com.stardeux.upprime.core.ui

import com.google.android.material.tabs.TabLayout

interface OnlyOnTabSelectedListener : TabLayout.OnTabSelectedListener {
    override fun onTabReselected(tab: TabLayout.Tab?) {}

    override fun onTabUnselected(tab: TabLayout.Tab?) {}
}
package com.stardeux.upprime.home.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.stardeux.upprime.R
import com.stardeux.upprime.core.model.mapToString
import com.stardeux.upprime.country.ui.SelectCountryActivity
import com.stardeux.upprime.search.ui.SearchActivity
import kotlinx.android.synthetic.main.fragment_tab_listing.*

class ReleaseTabListingFragment : Fragment(R.layout.fragment_tab_listing) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_settings -> {
                startActivity(SelectCountryActivity.newIntent(requireContext()))
                true
            }
            R.id.action_search -> {
                startActivity(SearchActivity.newIntent(requireContext()))
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

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
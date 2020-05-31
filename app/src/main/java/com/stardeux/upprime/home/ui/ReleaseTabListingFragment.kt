package com.stardeux.upprime.home.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.observeNotNull
import com.stardeux.upprime.core.extension.playStoreThisApp
import com.stardeux.upprime.core.model.mapToString
import com.stardeux.upprime.country.ui.SelectCountryActivity
import com.stardeux.upprime.rate.usecase.RateAppAnswer
import com.stardeux.upprime.search.ui.SearchActivity
import kotlinx.android.synthetic.main.fragment_tab_listing.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReleaseTabListingFragment : Fragment(R.layout.fragment_tab_listing) {

    private val releaseTabViewModel: ReleaseTabListingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pager.adapter = ReleaseTabAdapter(this)

        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = getReleaseTabAdapter().getReleaseType(position).mapToString(requireContext())
        }.attach()

        releaseTabViewModel.displayRateApp.observeNotNull(
            viewLifecycleOwner, ::handleDisplayRateApp
        )

        releaseTabViewModel.isFavorableActionReached.observeNotNull(
            viewLifecycleOwner, ::handleFavorableAction
        )
    }

    private fun handleFavorableAction(isReached: Boolean) {
        if (isReached) {
            val title = getString(R.string.rate_app_dialog_title, getString(R.string.app_name))
            val message = getString(R.string.rate_app_dialog_content, getString(R.string.app_name))
            AlertDialog.Builder(requireContext()).setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.rate_app_answer_yes) { _, _ ->
                    releaseTabViewModel.onRateAppAnswer(RateAppAnswer.YES)
                }.setNeutralButton(R.string.rate_app_answer_not_now) { _, _ ->
                    releaseTabViewModel.onRateAppAnswer(RateAppAnswer.NOT_NOW)
                }.setNegativeButton(R.string.rate_app_answer_never) { _, _ ->
                    releaseTabViewModel.onRateAppAnswer(RateAppAnswer.NEVER)
                }.show()
        }
    }

    private fun handleDisplayRateApp(display: Boolean) {
        if (display) {
            requireContext().playStoreThisApp()
        }
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
            R.id.item_share -> {
                shareApp()
                true
            }
            R.id.item_rate_app -> {
                releaseTabViewModel.onRateAppClicked()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun shareApp() {
        val appName = getString(R.string.app_name)
        val packageName = requireContext().packageName
        val playStoreUri = "market://details?id=$packageName"

        val shareTitle = getString(R.string.share_app_title, appName)
        val shareText = getString(R.string.share_app_text, playStoreUri)
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, shareTitle)
        startActivity(shareIntent)
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
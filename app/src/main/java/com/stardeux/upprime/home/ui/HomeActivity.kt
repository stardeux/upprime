package com.stardeux.upprime.home.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.commit
import com.google.android.gms.ads.*
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.getAdaptiveAdSize
import com.stardeux.upprime.core.extension.observeNotNull
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(R.layout.activity_home) {

    private val homeViewModel: HomeViewModel by viewModel()
    private var interstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)

        if (supportFragmentManager.findFragmentByTag(RELEASE_TAB_FRAGMENT_TAG) == null) {
            supportFragmentManager.commit {
                replace(
                    R.id.mainContent,
                    ReleaseTabListingFragment.newInstance(),
                    RELEASE_TAB_FRAGMENT_TAG
                )
            }
        }

        homeViewModel.isInterstitialLoaded.observeNotNull(this, ::handleInterstitialActivated)
        homeViewModel.displayInterstitial.observeNotNull(this, ::handleDisplayInterstitial)

        loadInterstitial()
        loadBottomBanner()
    }

    private fun handleInterstitialActivated(isInterActivated: Boolean) {
        if (isInterActivated) {
            loadInterstitial()
        }
    }

    private fun handleDisplayInterstitial(displayInter: Boolean) {
        if (displayInter) {
            interstitialAd?.show()
        }
    }

    private fun loadInterstitial() {
        interstitialAd = InterstitialAd(this).apply {
            adUnitId = getString(R.string.admob_interstitial_id)
            loadAd(AdRequest.Builder().build())

            adListener = object : AdListener() {
                override fun onAdLoaded() {
                    super.onAdLoaded()
                    homeViewModel.onInterstitialLoaded()
                }
            }
        }
    }

    private fun loadBottomBanner() {
        AdView(this).apply {
            adUnitId = getString(R.string.admob_home_banner_id)
            adSize = getAdaptiveAdSize()

            layoutParams = CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.MATCH_PARENT,
                CoordinatorLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                anchorId = R.id.mainContent
                anchorGravity = Gravity.BOTTOM
            }

            loadAd(AdRequest.Builder().build())

            homeCoordinator.addView(this)
        }
    }


    companion object {
        private const val RELEASE_TAB_FRAGMENT_TAG = "RELEASE_TAB_FRAGMENT_TAG"

        fun newIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }
}

package com.stardeux.upprime.home.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.commit
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.getAdaptiveAdSize
import com.stardeux.upprime.core.extension.observeNotNull
import com.stardeux.upprime.core.viewbinding.viewBinding
import com.stardeux.upprime.databinding.ActivityHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(R.layout.activity_home) {

    private val homeViewModel: HomeViewModel by viewModel()
    private var interstitialAd: InterstitialAd? = null

    private val binding by viewBinding(ActivityHomeBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)

        if (supportFragmentManager.findFragmentByTag(RELEASE_TAB_FRAGMENT_TAG) == null) {
            supportFragmentManager.commit {
                replace(
                    R.id.mainContent,
                    ReleaseTabListingFragment.newInstance(),
                    RELEASE_TAB_FRAGMENT_TAG
                )
            }
        }

        homeViewModel.isInterstitialActivated.observeNotNull(this, ::handleInterstitialActivated)
        homeViewModel.displayInterstitial.observeNotNull(this, ::handleDisplayInterstitial)

        loadBottomBanner()
    }

    private fun handleInterstitialActivated(isInterActivated: Boolean) {
        if (isInterActivated) {
            loadInterstitial()
        }
    }

    private fun handleDisplayInterstitial(displayInter: Boolean) {
        if (displayInter) {
            interstitialAd?.show(this)
        }
    }

    private fun loadInterstitial() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            this,
            getString(R.string.admob_interstitial_id),
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    interstitialAd = null
                }

                override fun onAdLoaded(newInterstitialAd: InterstitialAd) {
                    interstitialAd = newInterstitialAd
                    homeViewModel.onInterstitialLoaded()
                }
            })
    }

    private fun loadBottomBanner() {
        AdView(this).apply {
            adUnitId = getString(R.string.admob_home_banner_id)
            setAdSize(getAdaptiveAdSize())
            layoutParams = CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.MATCH_PARENT,
                CoordinatorLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                anchorId = R.id.mainContent
                anchorGravity = Gravity.BOTTOM
            }

            loadAd(AdRequest.Builder().build())

            binding.homeCoordinator.addView(this)
        }
    }


    companion object {
        private const val RELEASE_TAB_FRAGMENT_TAG = "RELEASE_TAB_FRAGMENT_TAG"

        fun newIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }
}

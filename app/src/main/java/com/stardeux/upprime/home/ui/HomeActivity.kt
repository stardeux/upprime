package com.stardeux.upprime.home.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.commit
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.getAdaptiveAdSize
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity(R.layout.activity_home) {

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

        loadBottomBanner()
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

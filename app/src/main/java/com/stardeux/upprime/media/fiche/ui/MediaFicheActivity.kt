package com.stardeux.upprime.media.fiche.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.doOnLayout
import androidx.core.view.doOnNextLayout
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.commit
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.applyCommonBack
import com.stardeux.upprime.core.extension.getAdaptiveAdSize
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_media_fiche.*
import kotlinx.android.synthetic.main.activity_media_fiche.toolbar

class MediaFicheActivity : AppCompatActivity(R.layout.activity_media_fiche) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        applyCommonBack(toolbar)

        val shortMedia: ShortMedia = requireNotNull(intent.getParcelableExtra(MEDIA_ARG))

        if (supportFragmentManager.findFragmentByTag(MEDIA_FICHE_FRAGMENT_TAG) == null) {
            supportFragmentManager.commit {
                replace(
                    R.id.ficheContent,
                    MediaFicheFragment.newInstance(shortMedia),
                    MEDIA_FICHE_FRAGMENT_TAG
                )
            }
        }

        loadBottomBanner()
    }

    private fun loadBottomBanner() {
        AdView(this).apply {
            adUnitId = getString(R.string.admob_fiche_banner_id)
            setAdSize(getAdaptiveAdSize())

            layoutParams = CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.MATCH_PARENT,
                CoordinatorLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                anchorId = R.id.ficheContent
                anchorGravity = Gravity.BOTTOM
            }

            loadAd(AdRequest.Builder().build())

            ficheCoordinator.addView(this)

            adListener = object : AdListener() {
                override fun onAdLoaded() {
                    super.onAdLoaded()

                    doOnLayout {
                        ficheContent.setPadding(0, 0, 0, height)
                    }
                }
            }

        }
    }

    companion object {
        private const val MEDIA_FICHE_FRAGMENT_TAG = "MEDIA_FICHE_FRAGMENT_TAG"
        private const val MEDIA_ARG = "MEDIA_ARG"

        fun newIntent(context: Context, shortMedia: ShortMedia): Intent {
            return Intent(context, MediaFicheActivity::class.java).apply {
                putExtra(MEDIA_ARG, shortMedia)
            }
        }
    }
}
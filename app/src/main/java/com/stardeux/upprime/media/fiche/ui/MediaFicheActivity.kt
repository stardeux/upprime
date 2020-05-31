package com.stardeux.upprime.media.fiche.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.applyCommonBack
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import kotlinx.android.synthetic.main.activity_media_fiche.*

class MediaFicheActivity : AppCompatActivity(R.layout.activity_media_fiche) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        applyCommonBack(toolbar)

        val shortMedia: ShortMedia = requireNotNull(intent.getParcelableExtra(MEDIA_ARG))

        if (supportFragmentManager.findFragmentByTag(MEDIA_FICHE_FRAGMENT_TAG) == null) {
            supportFragmentManager.commit {
                replace(R.id.ficheContent, MediaFicheFragment.newInstance(shortMedia), MEDIA_FICHE_FRAGMENT_TAG)
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
package com.stardeux.upprime.media.fiche.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import com.stardeux.upprime.R
import com.stardeux.upprime.media.common.ui.model.MediaItemUi

class MediaFicheActivity : AppCompatActivity(R.layout.activity_media_fiche) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    companion object {
        private const val MEDIA_UI_ARG = "MEDIA_UI_ARG"

        fun newIntent(context: Context, mediaItemUi: MediaItemUi): Intent {
            return Intent(context, MediaFicheActivity::class.java).apply {
                //putExtra(MEDIA_UI_ARG, mediaUi)
            }
        }
    }
}
package com.stardeux.upprime.media.fiche.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stardeux.upprime.R
import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.media.common.ui.model.MediaItemUi

class MediaFicheActivity : AppCompatActivity(R.layout.activity_media_fiche) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val imdbMediaId = requireNotNull(intent.getStringExtra(IMDB_ID_ARG))


    }

    companion object {
        private const val IMDB_ID_ARG = "MEDIA_UI_ARG"

        fun newIntent(context: Context, imdbId: ImdbId, mediaType: MediaType): Intent {
            return Intent(context, MediaFicheActivity::class.java).apply {
                putExtra(IMDB_ID_ARG, imdbId)
            }
        }
    }
}
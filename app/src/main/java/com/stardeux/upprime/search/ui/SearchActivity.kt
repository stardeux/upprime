package com.stardeux.upprime.search.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.applyCommonBack
import kotlinx.android.synthetic.main.activity_media_fiche.*

class SearchActivity : AppCompatActivity(R.layout.activity_search) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        applyCommonBack(toolbar)

        supportFragmentManager.commit {
            replace(R.id.searchContent, SearchFragment.newInstance())
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SearchActivity::class.java)
        }
    }

}
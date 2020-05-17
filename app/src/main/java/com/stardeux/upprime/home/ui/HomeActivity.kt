package com.stardeux.upprime.home.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.stardeux.upprime.R
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
    }

    companion object {
        private const val RELEASE_TAB_FRAGMENT_TAG = "RELEASE_TAB_FRAGMENT_TAG"

        fun newIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }
}

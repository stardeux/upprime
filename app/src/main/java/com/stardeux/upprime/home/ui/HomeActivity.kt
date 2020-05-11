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

        supportFragmentManager.commit {
            replace(R.id.mainContent, ReleaseTabListingFragment.newInstance())
        }
    }

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }
}

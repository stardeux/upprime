package com.stardeux.upprime.home.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.stardeux.upprime.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(R.layout.activity_home) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)
        toolbar.title = "cocou"

        supportFragmentManager.commit {
            replace(R.id.mainContent, ReleaseTabListingFragment.newInstance())
        }
    }
}

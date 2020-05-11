package com.stardeux.upprime.country.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

class SelectCountryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.commit {
            replace(android.R.id.content, SelectCountryFragment.newInstance())
        }
    }

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, SelectCountryActivity::class.java)
        }
    }
}
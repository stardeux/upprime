package com.stardeux.upprime.splash.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.observeNotNull
import org.koin.androidx.viewmodel.ext.android.viewModel
import  com.stardeux.upprime.core.extension.exhaustive
import com.stardeux.upprime.country.ui.SelectCountryActivity
import com.stardeux.upprime.home.ui.HomeActivity

class SplashActivity : AppCompatActivity(R.layout.activity_splash) {

    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashViewModel.navigationEvent.observeNotNull(this, ::onNavigationEvent)
    }

    private fun onNavigationEvent(navigationEvent: SplashViewModel.NavigationEvent) {
        val intent = when (navigationEvent) {
            SplashViewModel.NavigationEvent.Home -> HomeActivity.newIntent(this)
            SplashViewModel.NavigationEvent.Countries -> SelectCountryActivity.newIntent(this)
        }.exhaustive

        //startActivity(intent)
    }
}
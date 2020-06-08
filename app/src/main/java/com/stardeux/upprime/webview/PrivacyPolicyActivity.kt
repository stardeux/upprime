package com.stardeux.upprime.webview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.stardeux.upprime.core.analytics.AnalyticsValues
import com.stardeux.upprime.core.analytics.AnalyticsWrapper
import org.koin.android.ext.android.inject

class PrivacyPolicyActivity : WebViewActivity() {

    private val analyticsWrapper: AnalyticsWrapper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            analyticsWrapper.setCurrentScreen(this, AnalyticsValues.Screen.PRIVACY_POLICY)
        }
    }

    companion object {
        private const val POLICY_PRIVACY_URL = "https://sites.google.com/view/upprimepolicy/home"

        fun newIntent(context: Context): Intent {
            return Intent(context, PrivacyPolicyActivity::class.java).apply {
                putExtra(ARG_URL, POLICY_PRIVACY_URL)
            }
        }
    }
}
package com.stardeux.upprime.webview

import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.stardeux.upprime.R
import kotlinx.android.synthetic.main.activity_webview.*

abstract class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val url = getUrl()
        if (url == null) {
            finish()
            return
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                webviewLoader.isVisible = progress < 100
                webviewLoader.progress = progress
            }
        }

        webView.loadUrl(url)
        webView.settings.javaScriptEnabled = true

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun getUrl(): String? {
        return intent.getStringExtra(ARG_URL)
    }

    companion object {
        const val ARG_URL = "WebViewActivity_URL"
    }

}
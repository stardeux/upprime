package com.stardeux.upprime.webview

import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.stardeux.upprime.R
import com.stardeux.upprime.core.viewbinding.viewBinding
import com.stardeux.upprime.databinding.ActivityWebviewBinding

abstract class WebViewActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityWebviewBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url = getUrl()
        if (url == null) {
            finish()
            return
        }

        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                binding.webviewLoader.isVisible = progress < 100
                binding.webviewLoader.progress = progress
            }
        }

        binding.webView.loadUrl(url)
        binding.webView.settings.javaScriptEnabled = true

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
package com.master.marvelbook

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class CharactersDetail : AppCompatActivity() {

    companion object {
        const val url = "url"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters_detail)

        val url_detail = intent.getStringExtra(url)

        settings()
        loadWebsite(url_detail)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    private fun settings() {
        val web_setting: WebView = findViewById(R.id.web_view)
        web_setting.settings.javaScriptEnabled = true
        web_setting.settings.allowContentAccess = true
        web_setting.settings.safeBrowsingEnabled = true
        web_setting.settings.useWideViewPort = true
        web_setting.settings.loadsImagesAutomatically = true
        web_setting.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        web_setting.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        web_setting.settings.setEnableSmoothTransition(true)
        web_setting.settings.domStorageEnabled = true
    }

    private fun loadWebsite(url_detail: String?) {

        val web_loading: WebView = findViewById(R.id.web_view)
        val progressBar: ProgressBar = findViewById(R.id.progress_bar_webview)
        progressBar.max = 100

        web_loading.webChromeClient = object: WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                val progressBar: ProgressBar = findViewById(R.id.progress_bar_webview)
                progressBar.visibility = View.VISIBLE
                progressBar.progress = newProgress
                if (newProgress == 100) {
                    progressBar.visibility = View.GONE
                }
                super.onProgressChanged(view, newProgress)
            }
        }

        web_loading.webViewClient = object: WebViewClient() {
            override fun shouldOverrideUrlLoading( view: WebView?, URL: String?): Boolean {
                view?.loadUrl(URL.toString())
                progressBar.visibility = View.VISIBLE
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.visibility = View.GONE
            }
        }
        web_loading.loadUrl(url_detail.toString())
    }
}

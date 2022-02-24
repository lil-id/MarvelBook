package com.master.marvelbook

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class CharactersDetail : AppCompatActivity() {

    companion object {
        const val URL = "url"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters_detail)

        val url_detail = intent.getStringExtra(URL)
        webViewSetup(url_detail)


    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSetup(url_detail: String?) {

        val webHolder: WebView = findViewById(R.id.web_view)

        webHolder.webViewClient = WebViewClient()

        webHolder.apply {
            loadUrl("$url_detail")
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
        }
    }
}
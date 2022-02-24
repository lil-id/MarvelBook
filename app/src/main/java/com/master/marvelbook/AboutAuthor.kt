package com.master.marvelbook

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class AboutAuthor : AppCompatActivity(), View.OnClickListener {

    private lateinit var instagram : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move)

        instagram = findViewById(R.id.button_follow)
        instagram.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.button_follow -> {
                val urlIntent = Intent(Intent.ACTION_VIEW,  Uri.parse("https://www.instagram.com/myid.lil/"))
                startActivity(urlIntent)
            }
        }
    }
}
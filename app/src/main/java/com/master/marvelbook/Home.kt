package com.master.marvelbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.master.marvelbook.data.CharacterData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerView = findViewById(R.id.rv_heroes)
        progressBar = findViewById(R.id.progress_bar_home)
        progressBar.visibility = View.VISIBLE
        recyclerView.layoutManager = LinearLayoutManager(this)

        APIService.instance.getCharacters(Constants.limit, Constants.ts, Constants.APIKey, Constants.hash)
            .enqueue(object: Callback<CharacterData> {
                override fun onResponse(
                    call: Call<CharacterData>,
                    response: Response<CharacterData>
                ) {
                    recyclerView.adapter =
                        response.body()?.data?.let {CharacterAdapter(it.results, this@Home)}
                    progressBar.visibility = View.GONE
                }

                override fun onFailure(call: Call<CharacterData>, t: Throwable) {
                    progressBar.visibility = View.GONE
                }

            })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.about_person, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }
    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.about_author -> {
                val detailAuthor = Intent(this@Home, AboutAuthor::class.java)  // new activity in MainActivity
                startActivity(detailAuthor)
            }
        }
    }
}
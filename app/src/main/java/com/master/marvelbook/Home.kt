package com.master.marvelbook

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.master.marvelbook.data.CharacterData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: Skeleton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerView = findViewById(R.id.rv_heroes)
        progressBar = recyclerView.applySkeleton(R.layout.item_row_hero)
        progressBar.showSkeleton()
        recyclerView.layoutManager = LinearLayoutManager(this)

        APIService.instance.getCharacters(Constants.limit, Constants.ts, Constants.APIKey, Constants.hash)
            .enqueue(object: Callback<CharacterData> {
                override fun onResponse(
                    call: Call<CharacterData>,
                    response: Response<CharacterData>
                ) {
                    progressBar.showOriginal()
                    recyclerView.adapter =
                        response.body()?.data?.let {CharacterAdapter(it.results, this@Home)}
                }

                override fun onFailure(call: Call<CharacterData>, t: Throwable) {
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
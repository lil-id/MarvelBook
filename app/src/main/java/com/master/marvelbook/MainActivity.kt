package com.master.marvelbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.master.marvelbook.data.CharacterData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv_heroes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        APIService.instance.getCharacters(Constants.ts, Constants.APIKey, Constants.hash)
            .enqueue(object: Callback<CharacterData> {
                override fun onResponse(
                    call: Call<CharacterData>,
                    response: Response<CharacterData>
                ) {
                    recyclerView.adapter = CharacterAdapter(response.body()!!.data.results, this@MainActivity)
                }

                override fun onFailure(call: Call<CharacterData>, t: Throwable) {
                }

            })
    }
}
package com.master.marvelbook

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIService {

    val instance: MarvelInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(constants.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        instance = retrofit.create(MarvelInterface::class.java)
    }
}
package com.master.marvelbook

import com.master.marvelbook.data.CharacterData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelInterface {
    @GET("characters")
    fun getCharacters(
        @Query("ts") ts: String?,
        @Query("apiKey") apiKey: String?,
        @Query("hash") hash: String?,
    ) : Call<CharacterData>
}
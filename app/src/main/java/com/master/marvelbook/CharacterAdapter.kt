package com.master.marvelbook

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.master.marvelbook.data.Result
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CharacterAdapter(val data: List<Result>, val context: Context): RecyclerView.Adapter<CharacterHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_row_hero, parent, false
        )
        return CharacterHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val character = data[position]
        holder.characterName.text = character.name
        holder.characterAbout.text = character.description

        val img = "${character.thumbnail.path}/portrait_xlarge.${character.thumbnail.extension}"

        Glide.with(context).load(img).into(holder.characterImg)

        val move = holder.charactersDetail
        move.setOnClickListener {
            onClick(data[position].urls[1].url)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun onClick(urlDetail: String) {

        val dataUrl = urlDetail
        val moveIntent = Intent(
            context,
            CharactersDetail::class.java
        )  // new activity from a non MainActivity class
        moveIntent.putExtra(CharactersDetail.URL, dataUrl)
        context.startActivity(moveIntent)
    }
}
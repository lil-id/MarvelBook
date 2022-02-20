package com.master.marvelbook

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class CharacterHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val characterName: TextView = itemView.findViewById(R.id.tv_item_name)
    val characterAbout: TextView = itemView.findViewById(R.id.tv_item_detail)
    val characterImg: CircleImageView = itemView.findViewById(R.id.img_item_photo)
}
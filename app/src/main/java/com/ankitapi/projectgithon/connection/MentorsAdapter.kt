package com.ankitapi.projectgithon.connection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ankitapi.projectgithon.R
import com.bumptech.glide.Glide

class MentorsAdapter(private val mentors_ArrayList:ArrayList<MentorViewModel>): RecyclerView.Adapter<MentorsAdapter.MentorsViewHolder>() {
    inner class MentorsViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val mentorImage:ImageView = itemView.findViewById(R.id.mentors_image_activity)
        val mentorName:TextView = itemView.findViewById(R.id.mentor_name_activity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorsViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.mentors_item_activity,parent,false)
        return MentorsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mentors_ArrayList.size
    }

    override fun onBindViewHolder(holder: MentorsViewHolder, position: Int) {
        holder.mentorName.text = mentors_ArrayList[position].mentorName
        val imgUrl = "http://ankitapi.xyz/EduGo/mentorsImage/"+mentors_ArrayList[position].mentorImage
        Glide.with(holder.itemView.context).load(imgUrl).placeholder(R.drawable.logo).into(holder.mentorImage)
    }
}
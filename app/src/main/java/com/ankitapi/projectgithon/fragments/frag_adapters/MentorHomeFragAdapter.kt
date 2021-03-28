package com.ankitapi.projectgithon.fragments.frag_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ankitapi.projectgithon.R
import com.ankitapi.projectgithon.connection.MentorViewModel
import com.bumptech.glide.Glide

class MentorHomeFragAdapter(val mentorArrayList : ArrayList<MentorViewModel>) : RecyclerView.Adapter<MentorHomeFragAdapter.MentorHomeViewModel>() {
    inner class MentorHomeViewModel(itemView : View) : RecyclerView.ViewHolder(itemView){
        val mentorNameTextView : TextView = itemView.findViewById(R.id.mentor_name)
        val mentorImage : ImageView = itemView.findViewById(R.id.mentor_image)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorHomeViewModel {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mentors_home_item_layout,parent,false)
        return MentorHomeViewModel(view)
    }


    override fun onBindViewHolder(holder: MentorHomeViewModel, position: Int) {
        holder.mentorNameTextView.text = mentorArrayList[position].mentorName
        val mentorImageURL = "http://ankitapi.xyz/EduGo/mentorsImage/"+mentorArrayList[position].mentorImage
        Glide.with(holder.itemView.context).load(mentorImageURL).placeholder(R.drawable.logo).into(holder.mentorImage)
    }


    override fun getItemCount(): Int {
        return mentorArrayList.size
    }
}
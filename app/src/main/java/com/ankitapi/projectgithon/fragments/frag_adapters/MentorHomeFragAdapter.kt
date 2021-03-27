package com.ankitapi.projectgithon.fragments.frag_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ankitapi.projectgithon.R
import com.ankitapi.projectgithon.connection.MentorViewModel

class MentorHomeFragAdapter(val mentorArrayList : ArrayList<MentorViewModel>) : RecyclerView.Adapter<MentorHomeFragAdapter.MentorHomeViewModel>() {
    inner class MentorHomeViewModel(itemView : View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorHomeViewModel {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mentors_home_item_layout,parent,false)
        return MentorHomeViewModel(view)
    }


    override fun onBindViewHolder(holder: MentorHomeViewModel, position: Int) {
        TODO("Not yet implemented")
    }


    override fun getItemCount(): Int {
        return mentorArrayList.size
    }
}
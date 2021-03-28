package com.ankitapi.projectgithon.fragments.frag_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ankitapi.projectgithon.GDViewModel
import com.ankitapi.projectgithon.R

class TrendingTopicsFragAdapter(val topicsArrayList : ArrayList<GDViewModel>) : RecyclerView.Adapter<TrendingTopicsFragAdapter.TrendingTopicsViewModel>() {
    inner class TrendingTopicsViewModel(itemView : View): RecyclerView.ViewHolder(itemView){
        val topicTextView : TextView = itemView.findViewById(R.id.first_textview)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingTopicsViewModel {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trending_topic_item , parent , false)
        return TrendingTopicsViewModel(view)
    }


    override fun onBindViewHolder(holder: TrendingTopicsViewModel, position: Int) {
        holder.topicTextView.text = topicsArrayList[position].topic

        holder.itemView.setOnClickListener {

        }
    }


    override fun getItemCount(): Int {
        return topicsArrayList.size
    }
}
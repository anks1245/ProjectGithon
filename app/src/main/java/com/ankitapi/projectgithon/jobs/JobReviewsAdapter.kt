package com.ankitapi.projectgithon.jobs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ankitapi.projectgithon.R

class JobReviewsAdapter(private val jobReviewArrayList: ArrayList<JobReviewViewModel>) : RecyclerView.Adapter<JobReviewsAdapter.JobReviewViewHolder>() {
    inner class JobReviewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val userNameTextView : TextView = itemView.findViewById(R.id.user_Name_review)
        val userReviewTextView : TextView = itemView.findViewById(R.id.user_Review)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_item_layout,parent,false)
        return JobReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobReviewViewHolder, position: Int) {
        holder.userNameTextView.text = jobReviewArrayList[position].userName
        holder.userReviewTextView.text = jobReviewArrayList[position].review
    }

    override fun getItemCount(): Int {
        return jobReviewArrayList.size
    }
}
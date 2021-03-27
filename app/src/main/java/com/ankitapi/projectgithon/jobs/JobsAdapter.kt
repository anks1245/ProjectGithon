package com.ankitapi.projectgithon.jobs

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class JobsAdapter(private val jobsArrayList: ArrayList<JobsViewModel>): RecyclerView.Adapter<JobsAdapter.JObsAdapterViewHolder>() {
    inner class JObsAdapterViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JObsAdapterViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return jobsArrayList.size
    }

    override fun onBindViewHolder(holder: JObsAdapterViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}
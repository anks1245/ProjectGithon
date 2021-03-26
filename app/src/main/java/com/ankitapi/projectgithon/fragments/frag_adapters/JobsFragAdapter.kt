package com.ankitapi.projectgithon.fragments.frag_adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ankitapi.projectgithon.jobs.JobsViewModel

class JobsFragAdapter(val jobsModelArrayList: ArrayList<JobsViewModel>) :
    RecyclerView.Adapter<JobsFragAdapter.JobsViewHolder>() {
    inner class JobsViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: JobsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}
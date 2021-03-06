package com.ankitapi.projectgithon.jobs

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ankitapi.projectgithon.R
import com.ankitapi.projectgithon.helper.toast

class JobsAdapter(private val jobsArrayList: ArrayList<JobsViewModel>): RecyclerView.Adapter<JobsAdapter.JObsAdapterViewHolder>() {
    inner class JObsAdapterViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val company_Name:TextView = itemView.findViewById(R.id.company_name)
        val posted_by:TextView = itemView.findViewById(R.id.posted_by_name)
        val requirement:TextView = itemView.findViewById(R.id.requirement_positions)
//        val jobLikeCount : TextView = itemView.findViewById()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JObsAdapterViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.job_and_internship_item_activity,parent,false)
        return JObsAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return jobsArrayList.size
    }

    override fun onBindViewHolder(holder: JObsAdapterViewHolder, position: Int) {
        holder.company_Name.text = jobsArrayList[position].companyName
        holder.posted_by.text =  jobsArrayList[position].jobUploadedBy
        holder.requirement.text = jobsArrayList[position].job_desc

        val jobImgUrl = "http://ankitapi.xyz/EduGo/image/" + jobsArrayList[position].jobImage
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,JobFullDescActivity::class.java)
            intent.putExtra("JobId",jobsArrayList[position].jobUplaodid)
            intent.putExtra("HrName",jobsArrayList[position].jobUploadedBy)
            intent.putExtra("CompanyImage",jobImgUrl)
            intent.putExtra("CompanyName",jobsArrayList[position].companyName)
            intent.putExtra("Description",jobsArrayList[position].job_desc)
            intent.putExtra("JObLikeCount" , jobsArrayList[position].job_like)
            it.context.startActivity(intent)
        }
    }
}
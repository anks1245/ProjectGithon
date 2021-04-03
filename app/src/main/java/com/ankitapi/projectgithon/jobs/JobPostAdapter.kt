package com.ankitapi.projectgithon.jobs

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ankitapi.projectgithon.R
import com.ankitapi.projectgithon.helper.JOB_LIKE
import com.ankitapi.projectgithon.helper.toast
import com.bumptech.glide.Glide
import org.json.JSONObject

class JobPostAdapter(val jobArrayList: ArrayList<JobsViewModel>) : RecyclerView.Adapter<JobPostAdapter.JobsViewHolder>() {
    inner class JobsViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val userName : TextView= itemView.findViewById(R.id.hr_name)
        val postImage : ImageView = itemView.findViewById(R.id.company_image)
        val jobLikeCount : TextView = itemView.findViewById(R.id.count)
        val companyName : TextView = itemView.findViewById(R.id.company_name)
        var jobLike : ToggleButton = itemView.findViewById(R.id.work_symbol)
//        val requirements : TextView = itemView.findViewById(R.id.requirement)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.job_post_activity,parent,false)
        return JobsViewHolder(view)
    }


    override fun onBindViewHolder(holder: JobsViewHolder, position: Int) {
        var requestQueue : RequestQueue = Volley.newRequestQueue(holder.itemView.context)
        holder.userName.text = jobArrayList[position].jobUploadedBy
        holder.jobLikeCount.text = jobArrayList[position].job_like
        holder.companyName.text = jobArrayList[position].companyName
        val imgurl = "http://ankitapi.xyz/EduGo/image/"+jobArrayList[position].jobImage
        Glide.with(holder.itemView.context).load(imgurl).placeholder(R.drawable.logo).into(holder.postImage)
        holder.jobLike.text = null
        holder.jobLike.textOn = null
        holder.jobLike.textOff = null
        var like = "0"
        val jobImgUrl = "http://ankitapi.xyz/EduGo/image/" + jobArrayList[position].jobImage
        holder.itemView.setOnClickListener {
            it.context.toast(jobArrayList[position].companyName)
            val intent = Intent(holder.itemView.context,JobFullDescActivity::class.java)
            intent.putExtra("HrName",jobArrayList[position].jobUploadedBy)
            intent.putExtra("CompanyImage",jobImgUrl)
            intent.putExtra("CompanyName",jobArrayList[position].companyName)
            intent.putExtra("Description",jobArrayList[position].job_desc)
            it.context.startActivity(intent)
        }
        holder.jobLike.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked){
                val stringRequest= object : StringRequest(Method.POST, JOB_LIKE , Response.Listener { response ->
                    var jsonResponse = JSONObject(response)
                    like = jsonResponse.getString("like_count")
                    holder.jobLikeCount.text = like
//                        holder.itemView.context.toast(response)
                }, Response.ErrorListener { error ->
                    holder.itemView.context.toast(error.message.toString())
                }){
                    override fun getParams(): MutableMap<String, String> {
                        val hashMap = HashMap<String,String>()
                        hashMap["job_upload_id"] = jobArrayList[position].jobUplaodid.toString()
                        hashMap["post_like"] = "true"
                        return hashMap
                    }
                }
                requestQueue.add(stringRequest)

            }else{
                like = (like.toInt() - 1).toString()
                holder.jobLikeCount.text = like
                val stringRequest= object : StringRequest(Method.POST, JOB_LIKE , Response.Listener { response ->
                    var jsonResponse = JSONObject(response)
//                    holder.jobLikeCount.text = like

//                    holder.itemView.context.toast(response)
                }, Response.ErrorListener { error ->
                    holder.itemView.context.toast(error.message.toString())
                }){
                    override fun getParams(): MutableMap<String, String> {
                        val hashMap = HashMap<String,String>()
                        hashMap["job_upload_id"] = jobArrayList[position].jobUplaodid.toString()
                        hashMap["post_like"] = "false"
                        return hashMap
                    }
                }
                requestQueue.add(stringRequest)
            }

        }


    }


    override fun getItemCount(): Int {
        return jobArrayList.size
    }
}
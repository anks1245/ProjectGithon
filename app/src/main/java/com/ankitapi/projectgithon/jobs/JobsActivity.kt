package com.ankitapi.projectgithon.jobs

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Request.*
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.ankitapi.projectgithon.helper.JOBS
import org.json.JSONArray

class JobsActivity : AppCompatActivity(){
    private lateinit var requestQueue: RequestQueue
    private var jobsArrayList:ArrayList<JobsViewModel> = ArrayList()
    private lateinit var jobsAdapter: JobsAdapter
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Jobs"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    fun loadData(){
        val stringRequest=StringRequest(Request.Method.POST, JOBS ,Response.Listener { response ->
            val jsonArray= JSONArray(response)
            for (i in 0 until jsonArray.length()){
                val jsonObject = jsonArray.getJSONObject(i)
                val jobUplaodid = jsonObject.getString("job_upload_id")
                val jobUploadedBy = jsonObject.getString("job_uploaded_by")
                val jobName = jsonObject.getString("job_name")
                val companyName = jsonObject.getString("company_name")
                val jobImage = jsonObject.getString("job_image")
                val job_desc = jsonObject.getString("job_desc")
                val job_valid = jsonObject.getString("job_valid")
                val job_type = jsonObject.getString("job_type")
                val job_like = jsonObject.getString("job_like")
                val job_applied = jsonObject.getString("job_applied")
                val job_uploaded_at = jsonObject.getString("job_uploaded_at")
                val jobs = JobsViewModel(jobUplaodid, jobUploadedBy, jobName, companyName, jobImage, job_desc, job_valid, job_type, job_like, job_applied, job_uploaded_at)
                jobsArrayList.add(jobs)
                jobsAdapter = JobsAdapter(jobsArrayList)
            }
        },Response.ErrorListener { error ->
            Log.e("error","Error")
        })
        requestQueue.add(stringRequest)
    }
}
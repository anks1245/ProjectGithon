package com.ankitapi.projectgithon.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ankitapi.projectgithon.R
import com.ankitapi.projectgithon.helper.JOBS
import com.ankitapi.projectgithon.helper.toast
import com.ankitapi.projectgithon.jobs.JobPostAdapter
import com.ankitapi.projectgithon.jobs.JobsAdapter
import com.ankitapi.projectgithon.jobs.JobsViewModel
import org.json.JSONArray


class JobPostFragment : Fragment() {
    lateinit var root: View
    private lateinit var recyclerView : RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var requestQueue: RequestQueue
    private var jobArrayList2 : ArrayList<JobsViewModel> = ArrayList()
    private lateinit var progressBar : ProgressBar
    private  lateinit var jobAdapter : JobPostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_job_post, container, false)
        recyclerView = root.findViewById(R.id.jobpost_fragment_recyclerView)
        swipeRefreshLayout = root.findViewById(R.id.swipeToRefreshJob)
        requestQueue = Volley.newRequestQueue(this.requireContext())

        loadData()
        swipeRefreshLayout.setOnRefreshListener {
            jobArrayList2.clear()
            swipeRefreshLayout.isRefreshing =false
            loadData()
        }
        return root
    }

    private fun loadData() {
        val stringRequest = StringRequest(
                Request.Method.POST, JOBS ,
                Response.Listener { response->
                    convertJson(response)
                },
                Response.ErrorListener { error->
                    context?.toast(error.message.toString())
                })
        requestQueue.add(stringRequest)
    }

    private fun convertJson(response: String?) {
        val jsonArray = JSONArray(response)
        context?.toast(jsonArray.length().toString())
        for( i in 0 until jsonArray.length()) {
            jsonArray.getJSONObject(i).apply {
                val jobId = getString("job_upload_id")
                val companyName = getString("company_name")
                val jobName = getString("job_name")
                Log.d("JobFrsagment", "on Response $i : $jobName")
                val uName = getString("job_uploaded_by")
                val jobImage = getString("job_image")
                val jobDesc = getString("job_desc")
                val jobValid = getString("job_valid")
                val jobType = getString("job_type")
                val jobLike = getString("job_like")
                val jobApplied = getString("job_applied")
                val jobUploadedAt = getString("job_uploaded_at")
                val mJob = JobsViewModel(jobId, uName, jobName, companyName, jobImage, jobDesc, jobValid, jobType, jobLike, jobApplied, jobUploadedAt)
                jobArrayList2.add(mJob)
                jobAdapter = JobPostAdapter(jobArrayList2)
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
                    adapter = jobAdapter
                }
            }
        }
    }


}
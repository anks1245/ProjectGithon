package com.ankitapi.projectgithon.jobs

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ankitapi.projectgithon.R
import com.ankitapi.projectgithon.helper.GET_JOB_REVIEW
import com.ankitapi.projectgithon.helper.JOB_LIKE
import com.ankitapi.projectgithon.helper.toast
import com.bumptech.glide.Glide
import org.json.JSONArray
import org.json.JSONObject

class JobFullDescActivity: AppCompatActivity() {
    private lateinit var HrName:TextView
    private lateinit var CompanyImg:ImageView
    private lateinit var CompanyName:TextView
    private lateinit var Description:TextView
    private lateinit var requestQueue: RequestQueue
    private var jobReviewArrayList : ArrayList<JobReviewViewModel> = ArrayList()
    private lateinit var jobReviewsAdapter:JobReviewsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var reviewtextView: TextView
    private lateinit var jobLikeToggleButton: ToggleButton
    private lateinit var jobLikes : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.company_page)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Jobs"

        HrName = findViewById(R.id.JobsHrName)
        CompanyImg = findViewById(R.id.companyImage)
        CompanyName = findViewById(R.id.companyName)
        Description = findViewById(R.id.JobsDescription)
        reviewtextView = findViewById(R.id.review_textView)
        jobLikeToggleButton = findViewById(R.id.work_symbol_fulldesc)
        jobLikes = findViewById(R.id.countLikes)
        requestQueue = Volley.newRequestQueue(this)
        recyclerView = findViewById(R.id.job_review_recycler_view)
        val jobId = intent.getStringExtra("JobId")
        val hrName = intent.getStringExtra("HrName")
        val companyImage = intent.getStringExtra("CompanyImage")
        val companyName = intent.getStringExtra("CompanyName")
        val desc = intent.getStringExtra("Description")
        val like = intent.getStringExtra("JObLikeCount")
        HrName.text = hrName.toString()
        Glide.with(this).load(companyImage.toString()).placeholder(R.drawable.logo).into(CompanyImg)
        CompanyName.text = companyName.toString()
        Description.text = desc.toString()
        loadReviews(jobId)
        jobLikeToggleButton.text = null
        jobLikeToggleButton.textOn = null
        jobLikeToggleButton.textOff = null
        jobLikes.text = like
        var likeUser = like
        jobLikeToggleButton.setOnCheckedChangeListener { _, b ->
            if (b){
                toast("liked")
                val stringRequest= object : StringRequest(Method.POST, JOB_LIKE , Response.Listener { response ->
                    var jsonResponse = JSONObject(response)
                    likeUser = jsonResponse.getString("like_count")
                    jobLikes.text = (likeUser?.toInt()!! + 1).toString()
//                        holder.itemView.context.toast(response)
                }, Response.ErrorListener { error ->
                    toast(error.message.toString())
                }){
                    override fun getParams(): MutableMap<String, String> {
                        val hashMap = HashMap<String,String>()
                        hashMap["job_upload_id"] = jobId.toString()
                        hashMap["post_like"] = "true"
                        return hashMap
                    }
                }
                requestQueue.add(stringRequest)
            }else{
                toast("Disliked")
                likeUser = (likeUser?.toInt()?.minus(1)).toString()

                val stringRequest= object : StringRequest(Method.POST, JOB_LIKE , Response.Listener { response ->
                    var jsonResponse = JSONObject(response)
//                    holder.jobLikeCount.text = like

//                    holder.itemView.context.toast(response)
                }, Response.ErrorListener { error ->
                    toast(error.message.toString())
                }){
                    override fun getParams(): MutableMap<String, String> {
                        val hashMap = HashMap<String,String>()
                        hashMap["job_upload_id"] = jobId.toString()
                        hashMap["post_like"] = "false"
                        return hashMap
                    }
                }
                requestQueue.add(stringRequest)
            }
        }
    }

    private fun loadReviews(jobId: String?) {
        val stringRequest = object : StringRequest(Request.Method.POST, GET_JOB_REVIEW,Response.Listener {  response ->
//            toast(response)
            val jsonArray = JSONArray(response)
            for (i in 0 until jsonArray.length()){
                val jsonObject = jsonArray.getJSONObject(i)
                if (!jsonObject.getBoolean("error")){
                    val jobId = jsonObject.getString("job_id")
                    val userName = jsonObject.getString("username")
                    val userReview = jsonObject.getString("review")
                    val postedAt = jsonObject.getString("posted_at")
                    val review = JobReviewViewModel(jobId,userName,userReview,postedAt)
                    jobReviewArrayList.add(review)
                    jobReviewsAdapter = JobReviewsAdapter(jobReviewArrayList)
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@JobFullDescActivity,LinearLayoutManager.VERTICAL,false)
                        adapter = jobReviewsAdapter
                    }
                }else{
                    toast(jsonObject.getString("message"))
                    reviewtextView.visibility = View.INVISIBLE
                }
            }
        },Response.ErrorListener { error ->
            toast(error.message.toString())
        }){
            override fun getParams(): MutableMap<String, String> {
                val hashMap = HashMap<String,String>()
                hashMap["review_id"] = jobId.toString()
                return hashMap
            }
        }
        requestQueue.add(stringRequest)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
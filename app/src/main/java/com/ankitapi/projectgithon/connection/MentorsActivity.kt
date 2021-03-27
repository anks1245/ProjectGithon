package com.ankitapi.projectgithon.connection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ankitapi.projectgithon.R
import com.ankitapi.projectgithon.helper.GET_MENTORS
import org.json.JSONArray
import java.lang.reflect.Method

class MentorsActivity : AppCompatActivity() {
    private lateinit var requestQueue: RequestQueue
    private var mentorsArrayList:ArrayList<MentorViewModel> = ArrayList()
    private lateinit var mentorsAdapter:MentorsAdapter
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mentors2)
        //Do not Touch
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Mentors"
        //
        recyclerView = findViewById(R.id.mentors_recyclerview)
        requestQueue = Volley.newRequestQueue(this)
        loadData()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    fun loadData(){
        val stringRequest = StringRequest(Request.Method.POST, GET_MENTORS,Response.Listener {response ->
            val jsonArray = JSONArray(response)
            for (i in 0 until jsonArray.length()){
                val jsonObject = jsonArray.getJSONObject(i)
                val mentor_id:String = jsonObject.getString("mentor_id")
                val mentor_name:String = jsonObject.getString("mentor_name")
                val mentor_image:String = jsonObject.getString("mentor_image")
                val mentor_desc:String = jsonObject.getString("mentor_desc")
                val mentor_email:String = jsonObject.getString("mentor_email")
                val mentor_details = MentorViewModel(mentorName = mentor_name ,
                                                    mentorImage =  mentor_image,
                                                    mentorDesc = mentor_desc,
                                                    mentorEmail = mentor_email)
                mentorsArrayList.add(mentor_details)
                mentorsAdapter = MentorsAdapter(mentorsArrayList)
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(this@MentorsActivity,LinearLayoutManager.VERTICAL,false)
                    adapter = mentorsAdapter
                }
            }
        },Response.ErrorListener {
            Log.e("error","Error")
        })
        requestQueue.add(stringRequest)
    }
}
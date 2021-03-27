package com.ankitapi.projectgithon.connection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.ankitapi.projectgithon.R
import com.ankitapi.projectgithon.helper.GET_MENTORS
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
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    fun loadData(){
        val stringRequest = StringRequest(Request.Method.POST, GET_MENTORS,Response.Listener {

        },Response.ErrorListener {

        })
    }
}
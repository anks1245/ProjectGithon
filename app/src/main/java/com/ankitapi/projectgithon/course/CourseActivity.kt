package com.ankitapi.projectgithon.course

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ankitapi.projectgithon.R
import com.ankitapi.projectgithon.helper.GET_COURSES
import com.ankitapi.projectgithon.helper.toast
import org.json.JSONArray
import java.lang.reflect.Array
import java.lang.reflect.Method

class CourseActivity : AppCompatActivity() {
    var TAG = "CourseActivity"
    private lateinit var requestQueue: RequestQueue
    private var courseArrsyList : ArrayList<CourseModel> = ArrayList()
    private lateinit var courseAdapter : CourseAdapter
    private lateinit var courseRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Courses"

        requestQueue = Volley.newRequestQueue(this)
        courseRecyclerView = findViewById(R.id.course_activity_recycler_view)

        loadData()

    }

    private fun loadData() {
        val stringRequest = StringRequest(Request.Method.POST, GET_COURSES , Response.Listener { response ->
//            toast(response)
            val jsonArray = JSONArray(response)
//            toast(jsonArray.length().toString())

            for (i in 0 until jsonArray.length()){
                val jsonObject = jsonArray.getJSONObject(i)
                val courseName = jsonObject.getString("course_name")
                val courseImage = jsonObject.getString("course_image")
                val courseDesc = jsonObject.getString("course_desc")
                val coursePrice = jsonObject.getString("course_price")
                Log.e(TAG , "onResponse $courseName")
                val courses = CourseModel(courseName , courseImage ,courseDesc , coursePrice)
                courseArrsyList.add(courses)

                courseAdapter = CourseAdapter(courseArrsyList)
                courseRecyclerView.apply {
                    layoutManager = LinearLayoutManager(this@CourseActivity,LinearLayoutManager.VERTICAL,false)
                    adapter = courseAdapter
                }

            }
        },Response.ErrorListener { error ->
            Log.e(TAG , "onErrorResponse ${error.message}")
        })

        requestQueue.add(stringRequest)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
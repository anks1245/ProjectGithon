package com.ankitapi.projectgithon.course

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ankitapi.projectgithon.R
import com.ankitapi.projectgithon.helper.GET_COURSES
import com.ankitapi.projectgithon.helper.toast
import org.json.JSONArray
import java.lang.reflect.Method

class CourseActivity : AppCompatActivity() {
    var TAG = "CourseActivity"
    private lateinit var requestQueue: RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Courses"

        requestQueue = Volley.newRequestQueue(this)

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
                Log.e(TAG , "onResponse $courseName")
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
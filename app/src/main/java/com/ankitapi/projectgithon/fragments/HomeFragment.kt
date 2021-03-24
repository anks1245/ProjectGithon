package com.ankitapi.projectgithon.fragments

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ankitapi.projectgithon.QuizActivity
import com.ankitapi.projectgithon.R
import com.ankitapi.projectgithon.connection.ConnectionActivity
import com.ankitapi.projectgithon.connection.MentorsActivity
import com.ankitapi.projectgithon.course.CourseActivity
import com.ankitapi.projectgithon.helper.GET_COURSES
import com.ankitapi.projectgithon.helper.GET_MENTORS
import com.ankitapi.projectgithon.helper.GET_TOPICS
import com.ankitapi.projectgithon.helper.toast
import com.ankitapi.projectgithon.jobs.JobsActivity
import com.google.android.material.button.MaterialButton

class HomeFragment : Fragment() {
    private val TAG = "HomeFragment"
    private lateinit var requestQueue : RequestQueue
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var viewJobsButton : MaterialButton
    private lateinit var viewCourseButton : MaterialButton
    private lateinit var askMentorButton : MaterialButton
    private lateinit var connectionButton : MaterialButton
    private lateinit var eduSpaceButton : MaterialButton
    private lateinit var skillsButton : MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        swipeRefreshLayout = view.findViewById(R.id.swipeToRefreshHome)
        viewJobsButton = view.findViewById(R.id.view_jobs_internship)
        viewCourseButton = view.findViewById(R.id.view_courses_from_home)
        askMentorButton = view.findViewById(R.id.ask_mentor_home)
        connectionButton = view.findViewById(R.id.connection_home)
        eduSpaceButton = view.findViewById(R.id.eduspace_home)
        skillsButton = view.findViewById(R.id.improve_your_skills)
        requestQueue = Volley.newRequestQueue(this.requireContext())

        viewJobsButton.setOnClickListener{
            startActivity(Intent(it.context, JobsActivity::class.java))
        }
        viewCourseButton.setOnClickListener {
            startActivity(Intent(it.context, CourseActivity::class.java))
        }
        askMentorButton.setOnClickListener {
            startActivity(Intent(it.context, MentorsActivity::class.java))
        }
        connectionButton.setOnClickListener {
            startActivity(Intent(it.context, ConnectionActivity::class.java))
        }
        eduSpaceButton.setOnClickListener {
            it.context.toast("yet to be implimented")
        }
        skillsButton.setOnClickListener {
            startActivity(Intent(it.context, QuizActivity::class.java))
        }

        loadTrendingTopics()
        loadCourses()
        loadMentors()

        swipeRefreshLayout.setOnRefreshListener {

            loadTrendingTopics()
            loadCourses()
            loadMentors()
            swipeRefreshLayout.isRefreshing = false
        }

        return view
    }


    private fun loadMentors() {
        val stringRequest =  StringRequest(Request.Method.POST, GET_COURSES, Response.Listener { response ->
            context?.toast(response)
        }, Response.ErrorListener { error ->
            Log.e(TAG,"onResponseError ${error.message}")
        } )
        requestQueue.add(stringRequest)
    }

    private fun loadCourses() {
        val stringRequest =  StringRequest(Request.Method.POST, GET_MENTORS, Response.Listener { response ->
            context?.toast(response)
        }, Response.ErrorListener { error ->
            Log.e(TAG,"onResponseError ${error.message}")
        } )
        requestQueue.add(stringRequest)
    }

    private fun loadTrendingTopics() {
        val stringRequest =  StringRequest(Request.Method.POST, GET_TOPICS, Response.Listener { response ->
            context?.toast(response)
        }, Response.ErrorListener { error ->
            Log.e(TAG,"onResponseError ${error.message}")
        } )
        requestQueue.add(stringRequest)
    }

}
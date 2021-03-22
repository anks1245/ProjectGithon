package com.ankitapi.projectgithon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CourseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Courses"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
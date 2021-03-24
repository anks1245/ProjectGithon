package com.ankitapi.projectgithon.connection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ankitapi.projectgithon.R

class MentorsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mentors2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Mentors"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
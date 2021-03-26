package com.ankitapi.projectgithon.jobs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class JobsActivity : AppCompatActivity(){
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

}
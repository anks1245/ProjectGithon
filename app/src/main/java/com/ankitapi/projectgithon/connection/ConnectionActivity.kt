package com.ankitapi.projectgithon.connection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ankitapi.projectgithon.R

class ConnectionActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connection)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Connection"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
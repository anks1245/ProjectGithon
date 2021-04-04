package com.ankitapi.projectgithon.connection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ankitapi.projectgithon.R

class ConnectionChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connection_chat)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val userName = intent.getStringExtra("UserName")
        supportActionBar?.title = userName

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
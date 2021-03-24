package com.ankitapi.projectgithon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Check Your Skill"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
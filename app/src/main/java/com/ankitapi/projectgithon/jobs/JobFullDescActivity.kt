package com.ankitapi.projectgithon.jobs

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ankitapi.projectgithon.R
import com.bumptech.glide.Glide

class JobFullDescActivity: AppCompatActivity() {
    private lateinit var HrName:TextView
    private lateinit var CompanyImg:ImageView
    private lateinit var CompanyName:TextView
    private lateinit var Description:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.company_page)
        HrName = findViewById(R.id.JobsHrName)
        CompanyImg = findViewById(R.id.companyImage)
        CompanyName = findViewById(R.id.companyName)
        Description = findViewById(R.id.JobsDescription)
        val hrName = intent.getStringExtra("HrName")
        val companyImage = intent.getStringExtra("CompanyImage")
        val companyName = intent.getStringExtra("CompanyName")
        val desc = intent.getStringExtra("Description")
        HrName.text = hrName.toString()
        Glide.with(this).load(companyImage.toString()).placeholder(R.drawable.logo).into(CompanyImg)
        CompanyName.text = companyName.toString()
        Description.text = desc.toString()
    }
}
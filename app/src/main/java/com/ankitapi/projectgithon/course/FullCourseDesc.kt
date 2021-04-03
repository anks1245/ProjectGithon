package com.ankitapi.projectgithon.course

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.ankitapi.projectgithon.R
import com.bumptech.glide.Glide

class FullCourseDesc : AppCompatActivity() {
    private lateinit var CourseImage:ImageView
    private lateinit var CourseName:TextView
    private lateinit var Price:TextView
    private lateinit var CourseDescription:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.course_description)
        val name = intent.getStringExtra("CourseName")
        val price = intent.getStringExtra("CoursePrice")
        val desc = intent.getStringExtra("CourseDesc")
        val img = intent.getStringExtra("ImageURL")
        CourseImage = findViewById(R.id.courseImage)
        CourseName = findViewById(R.id.courseName)
        Price = findViewById(R.id.coursePrice)
        CourseDescription = findViewById(R.id.courseDescription)
        Glide.with(this).load(img.toString()).placeholder(R.drawable.logo).into(CourseImage)
        CourseName.text = name.toString()
        Price.text = price.toString()
        CourseDescription.text = desc.toString()
    }
}
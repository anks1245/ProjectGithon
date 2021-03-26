package com.ankitapi.projectgithon.course

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ankitapi.projectgithon.R
import com.ankitapi.projectgithon.helper.toast
import com.bumptech.glide.Glide

class CourseAdapter(private val courseArrayList: ArrayList<CourseModel>) : RecyclerView.Adapter<CourseAdapter.CourseRecyclerViewHolder>() {

    inner class CourseRecyclerViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val courseName : TextView = itemView.findViewById(R.id.title_textview)
        val courseImage : ImageView = itemView.findViewById(R.id.image_course)
        val coursePrice : TextView = itemView.findViewById(R.id.course_price_item_layout)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.courses_item_activity,parent,false)
        return CourseRecyclerViewHolder(view)
    }


    override fun onBindViewHolder(holder: CourseRecyclerViewHolder, position: Int) {
        holder.courseName.text = courseArrayList[position].courseName
        holder.coursePrice.text = courseArrayList[position].coursePrice
        val desc = courseArrayList[position].courseDesc
        val imageUrl = "http://ankitapi.xyz/EduGo/courseImage/"+courseArrayList[position].courseImage
        Glide.with(holder.itemView.context).load(imageUrl).placeholder(R.drawable.logo).into(holder.courseImage)
        holder.itemView.setOnClickListener {
//            it.context.toast(courseArrayList[position].courseName)
            val intent = Intent(holder.itemView.context , FullCourseDesc::class.java)
            it.context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return courseArrayList.size
    }
}
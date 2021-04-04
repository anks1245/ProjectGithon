package com.ankitapi.projectgithon.fragments.frag_adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ankitapi.projectgithon.R
import com.ankitapi.projectgithon.course.CourseModel
import com.ankitapi.projectgithon.course.FullCourseDesc
import com.ankitapi.projectgithon.helper.toast
import com.bumptech.glide.Glide

class CourseFragAdapter(private val courseModelArrayList: ArrayList<CourseModel>) : RecyclerView.Adapter<CourseFragAdapter.CourseAdapterViewHolder>(){
    inner class CourseAdapterViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val courseName : TextView = itemView.findViewById(R.id.courses_name_item)
        val courseImage : ImageView = itemView.findViewById(R.id.courses_image)
        val coursePrice : TextView = itemView.findViewById(R.id.price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.get_courses,parent,false)
        return CourseAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseAdapterViewHolder, position: Int) {
        holder.courseName.text = courseModelArrayList[position].courseName
        holder.coursePrice.text = courseModelArrayList[position].coursePrice
        val desc = courseModelArrayList[position].courseDesc
        val imageUrl = "http://ankitapi.xyz/EduGo/courseImage/"+courseModelArrayList[position].courseImage
        Glide.with(holder.itemView.context).load(imageUrl).placeholder(R.drawable.logo).into(holder.courseImage)
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context , FullCourseDesc::class.java)
            intent.putExtra("CourseName",courseModelArrayList[position].courseName)
            intent.putExtra("CoursePrice",courseModelArrayList[position].coursePrice)
            intent.putExtra("ImageURL",imageUrl)
            intent.putExtra("CourseDesc",desc)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return courseModelArrayList.size
    }
}
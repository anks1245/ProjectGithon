package com.ankitapi.projectgithon.connection

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ankitapi.projectgithon.R

class ConnectionAdapter(private val usersArrayList: ArrayList<ConnectionViewModel>):RecyclerView.Adapter<ConnectionAdapter.ConnectionViewHolder>() {
    inner class ConnectionViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val userImg : ImageView = itemView.findViewById(R.id.user_image_connection)
        val usernameTextView : TextView = itemView.findViewById(R.id.user_name_connection)
        val userType : TextView = itemView.findViewById(R.id.user_position_connection)
        val userConnect : Button = itemView.findViewById(R.id.connect_button)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConnectionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.connection_item_activity,parent,false)
        return ConnectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConnectionViewHolder, position: Int) {
        holder.usernameTextView.text = usersArrayList[position].username
        holder.userType.text = usersArrayList[position].userType
        holder.userConnect.setOnClickListener {
            val intent = Intent(it.context , ConnectionChatActivity::class.java)
            intent.putExtra("UserName",usersArrayList[position].username)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return usersArrayList.size
    }
}
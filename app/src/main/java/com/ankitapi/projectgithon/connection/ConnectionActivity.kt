package com.ankitapi.projectgithon.connection

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ankitapi.projectgithon.R
import com.ankitapi.projectgithon.helper.GET_USERS
import com.ankitapi.projectgithon.helper.UNIQUE_ID
import org.json.JSONArray

class ConnectionActivity : AppCompatActivity(){
    private lateinit var requestQueue: RequestQueue
    private lateinit var sharedPreferences: SharedPreferences
    private var usersArrayList : ArrayList<ConnectionViewModel> = ArrayList()
    private lateinit var connectionAdapter : ConnectionAdapter
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connection)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Connection"
        requestQueue = Volley.newRequestQueue(this)
        sharedPreferences = getSharedPreferences("loginSharepref",Context.MODE_PRIVATE)
        recyclerView = findViewById(R.id.get_profiles_from_connection)

        loadAllUsers()
    }

    private fun loadAllUsers() {
        var username =""
        var userType = ""
        var userCompany =""
        var userPosition = ""
        var userImg = ""
        val stringRequest = StringRequest(Request.Method.POST, GET_USERS ,Response.Listener { response ->
            val jsonArray = JSONArray(response)
            for (i in 0 until jsonArray.length()){
                val jsonObject = jsonArray.getJSONObject(i)
                val uniqueId = jsonObject.getString("uniqueid")
                val userUniqueId = sharedPreferences.getString(UNIQUE_ID , "0")
                if (uniqueId == userUniqueId){
                    continue
                }else{
                    username = jsonObject.getString("name")
                    userType = jsonObject.getString("userType")
                    userCompany = jsonObject.getString("company")
                    userPosition = jsonObject.getString("position")
                    userImg = jsonObject.getString("img")
                }
                val users = ConnectionViewModel(username = username ,userType = userType ,usercompany = userCompany , userposition = userPosition,userImg = userImg)
                usersArrayList.add(users)
                connectionAdapter = ConnectionAdapter(usersArrayList)
                recyclerView.apply {
                    layoutManager = GridLayoutManager(this@ConnectionActivity,2,LinearLayoutManager.VERTICAL,false)
                    adapter = connectionAdapter
                }

            }
        },Response.ErrorListener { error ->

        })
        requestQueue.add(stringRequest)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
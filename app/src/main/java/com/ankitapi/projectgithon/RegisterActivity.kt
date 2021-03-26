package com.ankitapi.projectgithon

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ankitapi.projectgithon.helper.REGISTER_API

class RegisterActivity : AppCompatActivity() {
    private lateinit var toLoginActivity : TextView
    private lateinit var requestQueue: RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)
        toLoginActivity = findViewById(R.id.toLoginActivity)
        requestQueue = Volley.newRequestQueue(this)
        toLoginActivity.setOnClickListener {
            val intent = Intent(this , LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        val stringRequest = object :StringRequest(Method.POST, REGISTER_API,Response.Listener {
            
        },Response.ErrorListener {

        }){
            override fun getParams(): MutableMap<String, String> {
                val hashMap = HashMap<String,String>()
                return hashMap
            }
        }
        requestQueue.add(stringRequest)

    }
}
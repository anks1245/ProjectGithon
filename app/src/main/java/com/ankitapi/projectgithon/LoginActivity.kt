package com.ankitapi.projectgithon

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ankitapi.projectgithon.helper.LOGIN
import com.ankitapi.projectgithon.helper.toast
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private var TAG = "LoginActivity"
    private lateinit var email : EditText
    private lateinit var pass : EditText
    private lateinit var checkBox: CheckBox
    private lateinit var requestQueue :RequestQueue
    private lateinit var buttonLogin : Button
    private var isRemember = "0"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        email = findViewById(R.id.login_email_edittext)
        pass = findViewById(R.id.login_pass_edittext)
        checkBox = findViewById(R.id.remember)
        requestQueue = Volley.newRequestQueue(this)
        val getEmail = email.text
        val getPass = pass.text
        checkBox.setOnCheckedChangeListener { compoundButton, b ->
            isRemember = if (compoundButton.isChecked){
                "1"
            }else{
                "0"
            }
        }
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonLogin.setOnClickListener {
            val stringRequest = object : StringRequest(Request.Method.POST, LOGIN , Response.Listener { response ->
                Log.d(TAG , "onResponse $response")
                val jsonObject = JSONObject(response)
                val userJson = jsonObject.getJSONObject("0")
                val name = userJson.getString("name")

//                toast(name)
            },Response.ErrorListener {  error ->
                Log.d(TAG , "onResponse $error")
            }){
                override fun getParams(): MutableMap<String, String> {
                    val hashMap = HashMap<String , String>()
                    hashMap["email_id"] = getEmail.toString()
                    hashMap["password"] = getPass.toString()
                    hashMap["remember"] = isRemember
                    return  hashMap
                }
            }
            requestQueue.add(stringRequest)
            val intent = Intent(this , MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }
    }
}
package com.ankitapi.projectgithon

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ankitapi.projectgithon.helper.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private var TAG = "LoginActivity"
    private var sharepref = "loginSharepref"
    private lateinit var email : EditText
    private lateinit var pass : EditText
    private lateinit var checkBox: CheckBox
    private lateinit var requestQueue :RequestQueue
    private lateinit var buttonLogin : Button
    private var isRemember = "0"
    private lateinit var sharedPreferences :SharedPreferences
    private lateinit var progrssBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        email = findViewById(R.id.login_email_edittext)
        pass = findViewById(R.id.login_pass_edittext)
        checkBox = findViewById(R.id.remember)
        requestQueue = Volley.newRequestQueue(this)
        progrssBar = findViewById(R.id.login_progress)
        sharedPreferences = getSharedPreferences(sharepref,Context.MODE_PRIVATE)
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

        val sharePrefEditor : SharedPreferences.Editor = sharedPreferences.edit()
        buttonLogin.setOnClickListener {
            progrssBar.visibility = View.VISIBLE
            val stringRequest = object : StringRequest(Request.Method.POST, LOGIN , Response.Listener { response ->
                progrssBar.visibility =View.INVISIBLE
                sharePrefEditor.clear()
                sharePrefEditor.apply()
                Log.d(TAG , "onResponse1 $response")
                val jsonObject = JSONObject(response)
                val userJson = jsonObject.getJSONObject("0")
                val isRemember = jsonObject.getString("flag")
                val error = userJson.getBoolean("error")
                Log.d(TAG,"onResponse ${userJson.getBoolean("error")} $isRemember")
                if (!error){
                    val name = userJson.getString("name")
                    sharePrefEditor.putString(NAME , name)
                    val email = userJson.getString("email")
                    sharePrefEditor.putString(EMAIL , email)
                    val uniqueid = userJson.getString("uniqueid")
                    sharePrefEditor.putString(UNIQUE_ID , uniqueid)
                    val pass = userJson.getString("password")
                    sharePrefEditor.putString(PASS , pass)
                    val img = userJson.getString("img")
                    val uType = userJson.getString("userType")
                    sharePrefEditor.putString(UTYPE , uType)
                    val company = userJson.getString("company")
                    sharePrefEditor.putString(COMPANY , company)
                    val position = userJson.getString("position")
                    sharePrefEditor.putString(POSITION , position)
                    sharePrefEditor.putString(FLAG , isRemember)
                    sharePrefEditor.apply()
                    sharePrefEditor.commit()
                    val accountCreated = userJson.getString("account_created_at")
                    val intent = Intent(this , MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                    finish()
                }else{
                    toast(userJson.getString("message"))
                }
//                toast(name)
            },Response.ErrorListener {  error ->
                Log.d(TAG , "onResponseError ${error.message}")
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

        }
    }

}
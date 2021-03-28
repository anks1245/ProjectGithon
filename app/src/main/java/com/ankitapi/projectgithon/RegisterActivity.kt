package com.ankitapi.projectgithon

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ankitapi.projectgithon.helper.REGISTER_API
import com.ankitapi.projectgithon.helper.snackHelper
import com.ankitapi.projectgithon.helper.toast
import com.google.android.material.snackbar.Snackbar
import org.json.JSONObject
import java.util.logging.Handler

class RegisterActivity : AppCompatActivity() {
    private lateinit var toLoginActivity : TextView
    private lateinit var requestQueue: RequestQueue
    private lateinit var nameInput : EditText
    private lateinit var emailInput : EditText
    private lateinit var pass : EditText
    private lateinit var cpass : EditText
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)
        toLoginActivity = findViewById(R.id.toLoginActivity)
        nameInput = findViewById(R.id.name_edittext)
        emailInput = findViewById(R.id.login_email_register)
        pass = findViewById(R.id.login_pass_edittext)
        cpass = findViewById(R.id.confirm_pass_edittext)
        button = findViewById(R.id.buttonRegister)
        val name = nameInput.text
        val password = pass.text
        val cpassword = cpass.text
        val email = emailInput.text
        requestQueue = Volley.newRequestQueue(this)
        toLoginActivity.setOnClickListener {
            val intent = Intent(this , LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


        button.setOnClickListener {

            if (password.toString() != cpassword.toString()){
                cpass.error = "Password Didn't Match"
                return@setOnClickListener
            }else{
                registerAccount(name.toString(),email.toString(),cpassword.toString())
            }

        }

    }

    private fun registerAccount(name: String, email: String, pass: String) {
        toast(name + email + pass)
        val stringRequest = object :StringRequest(Method.POST, REGISTER_API,Response.Listener { response ->
            val jsonObject = JSONObject(response)
            var msg=""
            if (jsonObject.getBoolean("error")){
                msg = jsonObject.getString("message")
            }else{
                msg = jsonObject.getString("message")+". Please Login yourself to continue"
                val intent = Intent(this , LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            toast(msg)

        },Response.ErrorListener {error ->
                toast(error.message)
        }){
            override fun getParams(): MutableMap<String, String> {
                val hashMap = HashMap<String,String>()
                hashMap["name"] = name
                hashMap["email_id"] = email
                hashMap["password"] = pass
                hashMap["usertype"] = "seeker"

                return hashMap
            }
        }
        requestQueue.add(stringRequest)
    }
}
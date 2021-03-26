package com.ankitapi.projectgithon.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ankitapi.projectgithon.R
import com.ankitapi.projectgithon.helper.UNIQUE_ID
import com.ankitapi.projectgithon.helper.UPDATE_PROFILE
import com.ankitapi.projectgithon.helper.toast

class UpdateProfileFragment : Fragment() {
    private var userTypeDD : ArrayList<String> = ArrayList()
    private lateinit var empLayout : ConstraintLayout
    private lateinit var userTypeInput:Spinner
    private lateinit var requestQueue : RequestQueue
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var oldPassword : EditText
    private lateinit var newPassword : EditText
    private lateinit var confirmPassword : EditText
    private lateinit var company : EditText
    private lateinit var position : EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.update_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        oldPassword = view.findViewById(R.id.old_password_input)
        newPassword = view.findViewById(R.id.cf_password_input)
        confirmPassword = view.findViewById(R.id.confirm_new_password_input)
        company = view.findViewById(R.id.company_name_input)
        position = view.findViewById(R.id.position_input)
        var utype = ""
        val old = oldPassword.text
        val np = newPassword.text
        val cfp = confirmPassword.text
        val com = company.text
        val pos = position.text
        userTypeInput = view.findViewById(R.id.user_type_input)
        empLayout = view.findViewById(R.id.select_employee)
        requestQueue = Volley.newRequestQueue(view.context)
        sharedPreferences = view.context.getSharedPreferences("loginSharepref",Context.MODE_PRIVATE)
        userTypeDD.add("Seeker")
        userTypeDD.add("Employer")
        val un = sharedPreferences.getString(UNIQUE_ID,"0")
        if (userTypeInput != null){
            val adapter : ArrayAdapter<String> = ArrayAdapter(view.context,R.layout.support_simple_spinner_dropdown_item,userTypeDD)
            userTypeInput.adapter = adapter
        }
        val update : Button= view.findViewById(R.id.update)

        userTypeInput.onItemSelectedListener = object : AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                view.context.toast(userTypeDD[p2])
                if (userTypeDD[p2].equals("Employer",true)){
                    empLayout.visibility = View.VISIBLE
                }else{
                    empLayout.visibility = View.GONE
                }
                utype = userTypeDD[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                view.context.toast(userTypeDD[p2])
            }
        }

        update.setOnClickListener {
            it.context.toast("$old $np $cfp $com $pos $utype")
            val stringRequest = object :StringRequest(Method.POST, UPDATE_PROFILE ,Response.Listener {
                navController.navigate(R.id.action_updateProfileFragment_to_profileFragment)
            },Response.ErrorListener {

            }){
                override fun getParams(): MutableMap<String, String> {
                    val hashMap = HashMap<String , String>()
                    hashMap["unique_id"] = un.toString()
                    hashMap["password"] = cfp.toString()
                    hashMap["company"] = com.toString()
                    hashMap["position"] = pos.toString()
                    hashMap["usertype"] = utype
                    return hashMap
                }
            }
//            requestQueue.add(stringRequest)
        }
    }

}
package com.ankitapi.projectgithon.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ankitapi.projectgithon.R
import com.ankitapi.projectgithon.helper.EMAIL
import com.ankitapi.projectgithon.helper.NAME
import com.ankitapi.projectgithon.helper.UTYPE


class ProfileFragment : Fragment() {
    private lateinit var updateProfileButton : Button
    private lateinit var userName : TextView
    private lateinit var userEmail : TextView
    private lateinit var userType : TextView
    private  lateinit var sharedPreference : SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreference = view.context.getSharedPreferences("loginSharepref",Context.MODE_PRIVATE)
        updateProfileButton = view.findViewById(R.id.update_button)

        userName = view.findViewById(R.id.user_name)
        userEmail = view.findViewById(R.id.email_example)
        userType = view.findViewById(R.id.user_type)

        userName.text = sharedPreference.getString(NAME , "username")
        userType.text = sharedPreference.getString(UTYPE , "Seeker")
        userEmail.text = sharedPreference.getString(EMAIL , "xyz@gmail.com")

        val navController = Navigation.findNavController(view)
        updateProfileButton.setOnClickListener {
            navController.navigate(R.id.action_profileFragment_to_updateProfileFragment)
        }
    }

}
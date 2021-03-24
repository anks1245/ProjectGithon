package com.ankitapi.projectgithon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ankitapi.projectgithon.R


class ProfileFragment : Fragment() {
    private lateinit var updateProfileButton : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateProfileButton = view.findViewById(R.id.update_button)
        val navController = Navigation.findNavController(view)
        updateProfileButton.setOnClickListener {
            navController.navigate(R.id.action_profileFragment_to_updateProfileFragment)
        }
    }

}
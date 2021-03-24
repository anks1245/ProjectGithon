package com.ankitapi.projectgithon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.FragmentTransaction
import com.ankitapi.projectgithon.fragments.ProfileFragment

class ProfileActivity : AppCompatActivity() {
    private lateinit var frameLayout:FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val profileFragment = ProfileFragment()
        frameLayout = findViewById(R.id.profile_fragment_view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Profile"
        openFragment(profileFragment)
    }

    private fun openFragment(profileFragment: ProfileFragment) {
        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.profile_fragment_view,profileFragment)
//        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
//    override fun onBackPressed() {
//        onBackPressed()
//        super.onBackPressed()
//    }
}
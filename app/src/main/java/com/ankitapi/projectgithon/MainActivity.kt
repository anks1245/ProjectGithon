package com.ankitapi.projectgithon

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.HandlerCompat.postDelayed
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.ankitapi.projectgithon.fragments.ChatsFragment
import com.ankitapi.projectgithon.fragments.HomeFragment
import com.ankitapi.projectgithon.fragments.JobPostFragment
import com.ankitapi.projectgithon.helper.toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout : DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navigation : NavigationView
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var menuItem: MenuItem
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var profileButton1 : ImageView
    private  var  doublePressedback =false
//    private lateinit var profileButton2: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val homeFragment = HomeFragment()
        val jobFragment = JobPostFragment()
        val chatsFragment = ChatsFragment()
        toolbar = findViewById(R.id.toolbar)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigation = findViewById(R.id.nvView)
        profileButton1 = findViewById(R.id.buttonProfile)
        sharedPreferences = getSharedPreferences("loginSharepref",Context.MODE_PRIVATE)
//        drawerToggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.JobFragemt,R.string.homeFragment)
//        drawerLayout.setDrawerListener(drawerToggle)
//        drawerToggle.syncState()
//        profileButton2 = navigation.findViewById(R.id.view_profile_from_drawer)
        bottomNavigationView = findViewById(R.id.bottom_navigation)
    profileButton1.setOnClickListener {
        startActivity(Intent(this,ProfileActivity::class.java))
    }
        //start of header of drawer layout
        val hView : View = navigation.getHeaderView(0)
        val userNametextView : TextView= hView.findViewById(R.id.user_name)
        val viewProfileTextView : TextView = hView.findViewById(R.id.view_profile_from_drawer)
        userNametextView.text = "Ankit"
        viewProfileTextView.setOnClickListener {
            startActivity(Intent(this , ProfileActivity::class.java))
        }

        //end
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24)
        openFragment(homeFragment)
        toolbar.showOverflowMenu()
        val sharePrefEditor : SharedPreferences.Editor = sharedPreferences.edit()
        navigation.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.view_courses_activity -> {
                    startActivity(Intent(this,CourseActivity::class.java))
                    true
                }R.id.account_signOut -> {
                    sharePrefEditor.clear()
                    val intent = Intent(this,LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                    sharePrefEditor.apply()
                    finish()
                    true
                }else -> {
                    false
                }
            }
        }
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home_frag_button -> {
                    openFragment(homeFragment)
                    true
                }
                R.id.jobpost_frag_button -> {
                    openFragment(jobFragment)
                    true
                }
                R.id.chat_button -> {
                    openFragment(chatsFragment)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
    private fun openFragment(fragment : Fragment?){
        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container,fragment!!)
//        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.overflow_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (doublePressedback){
            super.onBackPressed()
            return
        }
        this.doublePressedback = true
        toast("double press to go back")
        Handler().postDelayed({
                                doublePressedback = false
        }, 2000)
    }
}


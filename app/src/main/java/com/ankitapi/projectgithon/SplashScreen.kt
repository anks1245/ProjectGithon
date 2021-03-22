package com.ankitapi.projectgithon

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Thread.sleep


class SplashScreen : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var textView : TextView
    private lateinit var progressSplash : ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        setContentView(R.layout.activity_splash_screen)
        textView = findViewById(R.id.text)
        imageView = findViewById(R.id.splash_logo_anim_pic)
        progressSplash = findViewById(R.id.progress_splash)
        val slideAnim = AnimationUtils.loadAnimation(this, R.anim.slide_anim)
        imageView.startAnimation(slideAnim)
        val transanim = AnimationUtils.loadAnimation(this,R.anim.splash_anim)
        textView.startAnimation(transanim)
        Handler().postDelayed({
            val intent = Intent(this@SplashScreen, LoginActivity::class.java)
            for (i in 0 until 100){
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    progressSplash.progress = i
                }
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }, 4000)

    }
}
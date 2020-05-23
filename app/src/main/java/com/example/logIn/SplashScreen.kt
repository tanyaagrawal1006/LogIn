package com.example.logIn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val background = object : Thread() {
            override fun run() {
                try {
                        Thread.sleep(2000)
                        val intent = Intent(baseContext, LogInActivity::class.java)
                        startActivity(intent)
                    } catch (e: Exception){
                        e.printStackTrace()
                    }
                }
        }
        background.start()
    }
}

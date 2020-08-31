package com.kiranam.android.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.kiranam.android.R
import com.kiranam.android.ui.ui.login.LoginActivity

class Splashscreen : AppCompatActivity() {

    private val SPLASH_TIME_OUT = 3000L //3sec


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            // close this activity
            finish()
        }, SPLASH_TIME_OUT)




    }

}
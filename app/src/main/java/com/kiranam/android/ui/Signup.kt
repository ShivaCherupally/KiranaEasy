package com.kiranam.android.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kiranam.android.R
import com.kiranam.android.databinding.ActivitySignUp
import com.kiranam.android.ui.ui.login.LoginActivity

class Signup : AppCompatActivity() {
    private var binding: ActivitySignUp? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)

        binding!!.registerBtn.setOnClickListener { view ->
            startActivity(Intent(this, OtpActivity::class.java))
        }

        binding!!.haveaccTxt.setOnClickListener { view ->
            startActivity(Intent(this, LoginActivity::class.java))
        }


    }
}
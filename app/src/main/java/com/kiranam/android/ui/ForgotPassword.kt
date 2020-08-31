package com.kiranam.android.ui

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kiranam.android.R
import com.kiranam.android.StudentListActivity
import com.kiranam.android.databinding.ActivityForgotBinding
import com.kiranam.android.utils.createToast

class ForgotPassword : AppCompatActivity() {

    private var binding: ActivityForgotBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot)

        var toast: Toast = Toast(this)
        binding!!.sendotplayout.visibility = View.VISIBLE

        binding!!.verifyotp.setOnClickListener { view ->
            binding!!.sendotplayout.visibility = View.GONE
            binding!!.otpsavelayout.visibility = View.VISIBLE

            toast.createToast(this, "Sent success", Gravity.CENTER_VERTICAL, Toast.LENGTH_LONG)
            //startActivity(Intent(this, LoginActivity::class.java))
        }

        binding!!.sendotp.setOnClickListener { view ->
            binding!!.otpsavelayout.visibility = View.GONE
            binding!!.passwordlayout.visibility = View.VISIBLE
            toast.createToast(this, "Saved success", Gravity.CENTER_VERTICAL, Toast.LENGTH_LONG)
        }

        binding!!.sendpassword.setOnClickListener { view ->
            toast.createToast(this, "Success", Gravity.CENTER_VERTICAL, Toast.LENGTH_LONG)
            startActivity(Intent(this, StudentListActivity::class.java))
        }


    }
}
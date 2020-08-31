package com.kiranam.android.ui

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kiranam.android.R
import com.kiranam.android.StudentListActivity
import com.kiranam.android.databinding.ActivityOtpBinding
import com.kiranam.android.utils.createToast

class OtpActivity : AppCompatActivity() {

    private var binding: ActivityOtpBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_otp)

        var toast: Toast = Toast(this)

        binding!!.verifynowbtn.setOnClickListener { view ->
            startActivity(Intent(this, StudentListActivity::class.java))
        }

        binding!!.resendbtn.setOnClickListener { view ->
            toast.createToast(this, "Otp send success", Gravity.CENTER_VERTICAL, Toast.LENGTH_LONG)
        }
    }
}
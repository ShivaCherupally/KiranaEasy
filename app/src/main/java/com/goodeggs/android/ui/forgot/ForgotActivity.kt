package com.goodeggs.android.ui.forgot

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.goodeggs.android.MainViewModel
import com.goodeggs.android.R
import com.goodeggs.android.ui.login.LoginActivity
import com.goodeggs.android.utils.Utility
import kotlinx.android.synthetic.main.activity_forgot.*


class ForgotActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_forgot)

        sendemailBtn.setOnClickListener {
            val usernameStr = emailIdEt.text.toString().trim()
            if (usernameStr.isNullOrBlank()) {
                Utility.showSnackBar(this, container, "Please Enter Email Id", 1)
            } else if (!Utility.isValidEmail(usernameStr)) {
                Utility.showSnackBar(this, container, "Please Enter Valid Email Id", 1)
            } else {
                loading.visibility = View.VISIBLE
                viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
                viewModel.fogotPasswordReq(usernameStr)?.observe(this, Observer { userInfo ->
                    if (userInfo?.message.equals("Success")) {
                        loading.visibility = View.GONE
                        Toast.makeText(applicationContext, "Successfully Password sent your Email Id", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                    } else {
                        loading.visibility = View.GONE
                        Toast.makeText(applicationContext, "Invalid Email id or your not registered ", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }


     /*   resendbtn.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }*/


    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}

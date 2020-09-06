package com.kiranam.android.ui.signup

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kiranam.android.R
import com.kiranam.android.databinding.ActivitySignupBinding
import com.kiranam.android.ui.signup.viewmodel.SignupViewModel
import com.kiranam.android.utils.CustomeProgressDialog

class Signup : AppCompatActivity() {
    private var binding: ActivitySignupBinding? = null
    var viewmodel: SignupViewModel? = null
    var customeProgressDialog: CustomeProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)

        viewmodel = ViewModelProviders.of(this).get(SignupViewModel::class.java)
        binding?.viewmodel = viewmodel
        customeProgressDialog = CustomeProgressDialog(this)
        initObservables()

        /* binding!!.registerBtn.setOnClickListener { view ->
             startActivity(Intent(this, OtpActivity::class.java))
         }

         binding!!.haveaccTxt.setOnClickListener { view ->
             startActivity(Intent(this, LoginActivity::class.java))
         }*/

    }

    private fun initObservables() {
        viewmodel?.progressDialog?.observe(this, Observer {
            if (it!!) customeProgressDialog?.show() else customeProgressDialog?.dismiss()
        })

        viewmodel?.userLogin?.observe(this, Observer { user ->
            Toast.makeText(this, "welcome, ${user?.token}", Toast.LENGTH_LONG).show()
        })
    }
}
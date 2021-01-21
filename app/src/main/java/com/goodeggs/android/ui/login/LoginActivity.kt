package com.goodeggs.android.ui.login

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.goodeggs.android.MainViewModel
import com.goodeggs.android.R
import com.goodeggs.android.bottommenu.BottomMenuNew
import com.goodeggs.android.ui.forgot.ForgotActivity
import com.goodeggs.android.ui.signup.SignUpActivity
import com.goodeggs.android.utils.AppPreferences
import com.goodeggs.android.utils.Utility
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    var face: Typeface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        //        password_signin.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
//        password_signin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        face = Typeface.createFromAsset(assets,
                "fonts/sf_compact_display/sf_medium.otf")
        passwordlogin.setTypeface(face)

        login.setOnClickListener {
            var usernameStr = username.text.toString().trim()
            var passwordStr = passwordlogin.text.toString().trim()

            if (usernameStr.isNullOrBlank()) {
                Utility.showSnackBar(this, container, "Please Enter Username", 1)
                /*else if (username.text.toString().length != 10) {

                    Utility.showSnackBar(this, container, "Please Enter Valid Mobile no", 1)
                }*/
            } else if (passwordStr.isNullOrBlank()) {
                Utility.showSnackBar(this, container, "Please Enter Password", 1)
            } else {
                loading.visibility = View.VISIBLE
                viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
                viewModel.getLoginResponse(username.text.toString(), passwordlogin.text.toString())?.observe(this, Observer { userInfo ->
                    loading.visibility = View.GONE
                    if (userInfo?.message.equals("Success")) {
                        //UserData(cityname=Tiruvuru, pincode=521235, user_id=520, email=sanju121@gmail.com, mobile=9949451566, fullname=Sanju, success=1, message=Success)
                        AppPreferences.isLogin = true
                        AppPreferences.userId = userInfo?.user_id.toString()
                        AppPreferences.fullname = userInfo?.fullname.toString()
                        AppPreferences.mobile = userInfo?.mobile.toString()
                        AppPreferences.password = passwordlogin?.text.toString()
                        AppPreferences.email = userInfo?.email.toString()
                      /*  if (userInfo?.pincode != null) {
                            AppPreferences.isAddressAvailable = true
                            AppPreferences.cityName = userInfo.cityname.toString()
                            AppPreferences.pincode = userInfo.pincode.toString()
                        } else {
                            AppPreferences.isAddressAvailable = false
                        }*/
                        Toast.makeText(applicationContext, "Success login", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext, BottomMenuNew::class.java))
                        finish()
                    } else {
                        AppPreferences.isLogin = false
                        Utility.showSnackBar(this, container, "Invalid Username or Password", 1)
                    }
                })
            }
        }

        /*signuptxt.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }*/

        forgotpwd.setOnClickListener {
            startActivity(Intent(this, ForgotActivity::class.java))
        }
        imageViewpasswordhide.setOnClickListener {
            showHidePassword(passwordlogin, imageViewpasswordhide)
        }
    }

    private fun showHidePassword(editText: EditText, imageView: ImageView) {
        val inputTypeValue = editText.inputType
        var passworddata = ""
        if (editText.text.toString() != null) {
            passworddata = editText.text.toString()
        }
        if (inputTypeValue == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
            editText.transformationMethod = PasswordTransformationMethod.getInstance()
            editText.inputType = InputType.TYPE_CLASS_TEXT
            editText.setTypeface(face)
            imageView.background = resources.getDrawable(R.drawable.ic_eye_hide)
            editText.setText("")
            editText.append(passworddata)
        } else {
            editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
            editText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
            editText.setTypeface(face)
            imageView.background = resources.getDrawable(R.drawable.ic_eye_unhide)
            editText.setText("")
            editText.append(passworddata)
        }
    }
}

package com.kiranam.android.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kiranam.android.R
import com.kiranam.android.bottommenu.BottomMenuActivity
import com.kiranam.android.fontstyles.ButtonMedium
import com.kiranam.android.fontstyles.EditTextMediumWithoutEmoji
import com.kiranam.android.fontstyles.EditTextRegular
import com.kiranam.android.ui.ForgotPassword
import com.kiranam.android.ui.signup.Signup
import com.kiranam.android.utils.Utility


class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private var container: RelativeLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val username = findViewById<EditTextRegular>(R.id.username)
        val password = findViewById<EditTextMediumWithoutEmoji>(R.id.passwordlogin)
        val login = findViewById<ButtonMedium>(R.id.login)
        val loading = findViewById<ProgressBar>(R.id.loading)

        val signuptxt = findViewById<TextView>(R.id.signuptxt)
        val forgotpwd = findViewById<TextView>(R.id.forgotpwd)

        container = findViewById<RelativeLayout>(R.id.container)

        loginViewModel = ViewModelProviders.of(this, LoginViewModelFactory())
                .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer
            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid
            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        /*  username.afterTextChanged {
              loginViewModel.loginDataChanged(
                      username.text.toString(),
                      password.text.toString()
              )
          }

          password.apply {
              afterTextChanged {
                  loginViewModel.loginDataChanged(
                          username.text.toString(),
                          password.text.toString()
                  )
              }

              setOnEditorActionListener { _, actionId, _ ->
                  when (actionId) {
                      EditorInfo.IME_ACTION_DONE ->
                          loginViewModel.login(
                                  username.text.toString(),
                                  password.text.toString()
                          )
                  }
                  false
              }

          }*/

        login.setOnClickListener {
//            loading.visibility = View.VISIBLE
            if (username.text.toString().length == 0) {
                Utility.showSnackBar(this, container, "Please Enter Mobile no", 1)
            } else if (username.text.toString().length != 10) {
                Utility.showSnackBar(this, container, "Please Valid Mobile no", 1)
            } else if (password.text.toString().length == 0) {
                Utility.showSnackBar(this, container, "Please Enter Password", 1)
            } else {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())
            }


//            Utility.showSnackBar(applicationContext, container, "Please Enter Mobile no", 2)

        }


        signuptxt.setOnClickListener {
            startActivity(Intent(this, Signup::class.java))
        }

        forgotpwd.setOnClickListener {
            startActivity(Intent(this, ForgotPassword::class.java))
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName

        Toast.makeText(
                applicationContext,
                "$welcome $displayName",
                Toast.LENGTH_LONG
        ).show()
        startActivity(Intent(this, BottomMenuActivity::class.java))

    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()

    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

        }
    })
}
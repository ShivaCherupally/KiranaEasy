package com.goodeggs.android.ui.signup

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.goodeggs.android.MainViewModel
import com.goodeggs.android.R
import com.goodeggs.android.ui.login.LoginActivity
import com.goodeggs.android.utils.Utility
import kotlinx.android.synthetic.main.signup_activity.*
import org.json.JSONObject


class SignUpActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var viewModel: MainViewModel
    var citiesArray = ArrayList<String>()
    var cityId: String? = null
    var citiesIds = ArrayList<String>()
    var face: Typeface? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.signup_activity)

        face = Typeface.createFromAsset(assets,
                "fonts/sf_compact_display/sf_medium.otf")
        passwordet.setTypeface(face)

        getAllLocations()


        locationSpn.onItemSelectedListener = this

        rigesterBtn.setOnClickListener {
            var fullname = fullnameet.text.toString().trim()
            var mobile = mobileet.text.toString().trim()
            var password = passwordet.text.toString().trim()
            var emailid = emailidet.text.toString().trim()

            /* fullname = "Satheesh"
             mobile = "9908104562"
             password = "satheesh"
             emailid = "satheesh@gmail.com"*/
            if (fullname.isNullOrBlank()) {
                Utility.showSnackBarold(this, middlelayout, "Please Enter full name", 2)
            } else if (mobile.isNullOrBlank()) {
                Utility.showSnackBarold(this, middlelayout, "Please Enter Mobile no", 2)
            } else if (mobile.length != 10) {
                Utility.showSnackBarold(this, middlelayout, "Please Enter Valid Mobile no", 2)
            } else if (password.isNullOrBlank()) {
                Utility.showSnackBarold(this, middlelayout, "Please Enter Password", 2)
            } else if (password.length <= 5) {
                Utility.showSnackBarold(this, middlelayout, "Please must be atleast 6 characters ", 2)
            } else if (emailid.isNullOrBlank()) {
                Utility.showSnackBarold(this, middlelayout, "Please Enter Email Id", 2)
            } else if (!Utility.isValidEmail(emailid)) {
                Utility.showSnackBarold(this, middlelayout, "Please enter valid Email Id", 2)
            } else if (cityId.isNullOrEmpty() || cityId.equals("0")) {
                Utility.showSnackBarold(this, middlelayout, "Please select location", 2)
            } else {
                loading.visibility = View.VISIBLE
                viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

                val user = JSONObject()
                user.put("fullname", fullname)
                user.put("mobile", mobile)
                user.put("email", emailid)
                user.put("password", password)
                user.put("cityId", cityId)

                viewModel.registerAppViewModel(user)?.observe(this, Observer { userInfo ->
                    loading.visibility = View.GONE
                    if (userInfo.message != null) {
                        if (userInfo.success == 1) {
                            Toast.makeText(this, "Successfully register", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this, LoginActivity::class.java))
                        } else {
                            Toast.makeText(applicationContext, "Failed register", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(applicationContext, "Mobile or EmailId already exists", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }

        signIn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        imageViewpasswordhide.setOnClickListener {
            showHidePassword(passwordet, imageViewpasswordhide)
        }
    }

    fun getAllLocations() {
        loading.visibility = View.VISIBLE
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getCityList().observe(this, Observer { citiesData ->
            loading.visibility = View.GONE
            if (citiesData.get(0).success == 1) {
                for (i in 0 until citiesData.size + 1) {
                    if (i == 0) {
                        citiesArray.add("Select")
                        citiesIds.add("0")
                    } else {
                        citiesIds.add(citiesData.get(i - 1).id)
                        citiesArray.add(citiesData.get(i - 1).city_name)
                    }

                }
                val adapter = ArrayAdapter(this,
                        android.R.layout.simple_spinner_item, citiesArray)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                locationSpn.adapter = adapter
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val text: String = parent?.getItemAtPosition(position).toString()
        cityId = citiesIds.get(position)
//        Toast.makeText(applicationContext, "Your Location $text, $cityId", Toast.LENGTH_SHORT).show()
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

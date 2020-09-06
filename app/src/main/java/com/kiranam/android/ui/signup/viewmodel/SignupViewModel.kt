package com.kiranam.android.ui.signup.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.kiranam.android.model.User
import com.kiranam.android.network.BackEndApi
import com.kiranam.android.network.WebServiceClient
import com.kiranam.android.utils.SingleLiveEvent
import com.kiranam.android.utils.Util

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupViewModel(application: Application) : AndroidViewModel(application), Callback<User> {

    var btnSelected: ObservableBoolean? = null
    var btnSignIn: ObservableBoolean? = null



    var fullname: ObservableField<String>? = null
    var mobile: ObservableField<String>? = null
    var password: ObservableField<String>? = null
    var email: ObservableField<String>? = null



    var progressDialog: SingleLiveEvent<Boolean>? = null
    var userLogin: MutableLiveData<User>? = null

    init {
        btnSelected = ObservableBoolean(false)
        progressDialog = SingleLiveEvent<Boolean>()
        fullname = ObservableField("")
        mobile = ObservableField("")
        password = ObservableField("")
        email = ObservableField("")

        userLogin = MutableLiveData<User>()
    }

    /*fun onEmailChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        btnSelected?.set(Util.isEmailValid(s.toString()) && password?.get()!!.length >= 8)
    }

    fun onPasswordChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        btnSelected?.set(Util.isEmailValid(password?.get()!!) && s.toString().length >= 8)
    }*/


    fun signupinfo() {

        /*if (Util.isEmailValid(fullname.toString()) && fullname?.get()!!.length >= 8){

        }else if (){

        }*/


        progressDialog?.value = true
        WebServiceClient.client.create(BackEndApi::class.java).LOGIN(email = email?.get()!!, password = password?.get()!!)
                .enqueue(this)

    }

    override fun onResponse(call: Call<User>?, response: Response<User>?) {
        progressDialog?.value = false
        userLogin?.value = response?.body()
    }

    override fun onFailure(call: Call<User>?, t: Throwable?) {
        progressDialog?.value = false
//        Toast.makeText(co, "welcome, ${user?.last_name}", Toast.LENGTH_LONG).show()
    }

    fun signIn(){

    }

}
package com.kiranam.android.network

import com.kiranam.android.Constants
import com.kiranam.android.model.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface BackEndApi {

    @FormUrlEncoded
    @POST(Constants.SIGN_UP)
    fun LOGIN(@Field("email") email: String, @Field("password") password: String): Call<User>


}


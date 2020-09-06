package com.kiranam.android.services

import com.kiranam.android.Constants
import com.kiranam.android.models.StudentListResponse
import com.kiranam.android.ui.data.model.SignupData
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IServices {

    @GET(Constants.GET_ALL_STUDENTS_URL)
    fun signup(@Query("SchoolID") SchoolID: String?): Observable<Response<StudentListResponse?>?>?


    @POST(Constants.SIGN_UP)
    fun signupReq(@Body signupData: SignupData?): Observable<Response<SignupData?>?>?


}
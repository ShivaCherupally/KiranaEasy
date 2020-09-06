package com.kiranam.android.services;


import com.kiranam.android.Constants;
import com.kiranam.android.models.StudentListResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 */
public interface IStudentService {

    @GET(Constants.GET_ALL_STUDENTS_URL)
    Observable<Response<StudentListResponse>> getAllStudentsBySchoolId(@Query("SchoolID") String SchoolID);

}

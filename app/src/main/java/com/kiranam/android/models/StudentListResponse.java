package com.kiranam.android.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class StudentListResponse {
    //    @SerializedName("StudentAllInfoList")
    @SerializedName("result")
    private List<Student> studentAllInfoList;

    public List<Student> getStudentAllInfoList() {
        return studentAllInfoList;
    }

    public void setStudentAllInfoList(List<Student> studentAllInfoList) {
        this.studentAllInfoList = studentAllInfoList;
    }
}

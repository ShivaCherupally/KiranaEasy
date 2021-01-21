package com.goodeggs.android.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ForgotData {
    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("success")
    @Expose
    var success: Int? = null

}
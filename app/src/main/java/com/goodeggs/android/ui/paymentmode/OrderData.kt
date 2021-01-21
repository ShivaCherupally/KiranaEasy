package com.goodeggs.android.ui.paymentmode

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderData {
    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("success")
    @Expose
    var success: Int? = null

    @SerializedName("order_id")
    @Expose
    var order_id: String? = null

}
package com.goodeggs.android.ui.paymentmode

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderReqData {
    @SerializedName("user_id")
    @Expose
    var user_id: String? = null

    @SerializedName("success")
    @Expose
    var success: Int? = null

    @SerializedName("order_id")
    @Expose
    var order_id: String? = null

}
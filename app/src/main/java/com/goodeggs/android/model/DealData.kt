package com.goodeggs.android.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DealData {
    @SerializedName("banner_title")
    @Expose
    var banner_title: String? = null

    @SerializedName("product_id")
    @Expose
    var product_id: String? = null

    @SerializedName("product_name")
    @Expose
    var product_name: String? = null

    @SerializedName("sale_price")
    @Expose
    var sale_price: String? = null

    @SerializedName("pic")
    @Expose
    var pic: String? = null

}
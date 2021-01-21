package com.goodeggs.android.model

import com.google.gson.annotations.SerializedName


data class AddCartData(
        @SerializedName("user_id") val user_id: String,
        @SerializedName("product_id") val product_id: String,
        @SerializedName("product_name") val product_name: String,
        @SerializedName("quantity") val quantity: String,
        @SerializedName("sale_price") val sale_price: String
)
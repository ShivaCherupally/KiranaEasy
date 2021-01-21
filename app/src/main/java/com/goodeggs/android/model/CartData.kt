package com.goodeggs.android.model


data class CartData(
        var product_id: String,
        var cart_id: String,
        var pack_qty: Int,
        var product_qty: Int,
        var success: Int,
        var product_name: String,
        var delivery_date: String


)
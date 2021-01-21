package com.goodeggs.android.model


data class MyOrderData(
        var order_unique_id: String,
        var order_id: String,
        var order_date: String,
        var grandtotal: String,
        var address: String,
        var landmark: String,
        var order_status: String,
        var success: Int
)
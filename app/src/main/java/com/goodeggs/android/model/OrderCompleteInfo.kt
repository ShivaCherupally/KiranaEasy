package com.goodeggs.android.model


data class OrderCompleteInfo(
        var op_id: String,
        var product_id: String,
        var product_name: String,
        var product_qty: Int,
        var pack_qty: Int,
        var success: Int,

        var orderedit : Boolean
)
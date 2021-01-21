package com.goodeggs.android.model


data class ProductData(
        var product_id: String,
        var stock: String,
        var min_qty: Int,
        var product_name: String,
        var product_url: String,
        var brand_name: String,
        var mrp: String,
        var sale_price: String,
        var pic: String,
        var discount: String,
        var success: Int,
        var isAdded: Boolean,
        var instock: Int


)
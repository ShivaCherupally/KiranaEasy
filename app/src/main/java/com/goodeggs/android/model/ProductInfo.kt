package com.goodeggs.android.model


data class ProductInfo(
        var product_id: String,
        var stock: String,
        var quantity: Int,
        var product_name: String,
        var product_url: String,
        var brand_name: String,
        var mrp: String,
        var sale_price: String,
        var pic: String,
        var discount: String,
        var instock: Int

)
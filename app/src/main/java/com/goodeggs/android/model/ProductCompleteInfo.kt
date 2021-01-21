package com.goodeggs.android.model


data class ProductCompleteInfo(
        var product_name: String,
        var brand_name: String,
        var product_url: String,
        var pic: String,
        var sale_price: String,
        var mrp: String,
        var overview: String,
        var description: String,
        var variant_type: String,
        var variation: String,
        var discount: String,
        var instock: Int
)
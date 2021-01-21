package com.goodeggs.android.model


data class ProductTypesData(
        var product_id: String,
        var product_name: String,
        var pic: String,
        var success: Int,

        var packqty: Int,
        var product_qty: Int,
        var cart_count: Int,

        var pack_qty: List<PackLiistData>

)

class PackLiistData {
    lateinit var pack_qty: String
}

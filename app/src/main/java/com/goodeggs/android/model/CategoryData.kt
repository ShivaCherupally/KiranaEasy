package com.goodeggs.android.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CategoryData {
    @SerializedName("category_name")
    @Expose
    var category_name: String? = null

    @SerializedName("category_id")
    @Expose
    var category_id: String? = null

    @SerializedName("tag_line")
    @Expose
    var tag_line: String? = null

    @SerializedName("pic")
    @Expose
    var pic: String? = null

}
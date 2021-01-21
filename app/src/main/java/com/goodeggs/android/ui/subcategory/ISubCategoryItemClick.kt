package com.goodeggs.android.ui.subcategory

import com.goodeggs.android.model.SubCategoryInfo

interface ISubCategoryItemClick {
    fun onItemView(position: Int, subCategoryInfo: SubCategoryInfo)
}
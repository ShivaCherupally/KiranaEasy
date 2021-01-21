package com.goodeggs.android.ui.home

import android.view.View
import com.goodeggs.android.model.CategoryData
import com.goodeggs.android.model.DealData
import com.goodeggs.android.model.ProductTypesData

interface IProductAddClick {
    fun onAddItemClick(view: View, categoryData: ProductTypesData)
}
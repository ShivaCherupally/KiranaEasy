package com.goodeggs.android.ui.home

import android.view.View
import com.goodeggs.android.model.CartData
import com.goodeggs.android.model.CategoryData
import com.goodeggs.android.model.DealData
import com.goodeggs.android.model.ProductTypesData

interface ICartRemoveClick {
    fun onRemoveItemClick(view: View, categoryData: CartData)
}
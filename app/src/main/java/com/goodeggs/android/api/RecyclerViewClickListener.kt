package com.goodeggs.android.api

import android.view.View
import com.goodeggs.android.model.CategoryData
import com.goodeggs.android.model.DealData

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(view: View, categoryData: CategoryData)
    fun onRecyclerViewItemDealsClick(view: View, dealData: DealData)
}
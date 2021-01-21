package com.goodeggs.android.ui.deals

import com.goodeggs.android.model.DealData

interface IDealViewClickListener {
    fun onItemDealsClick(position: Int, dealData: DealData)
}
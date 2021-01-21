package com.goodeggs.android.ui.myorders

import com.goodeggs.android.model.MyOrderData

interface IOrderViewClickListener {
    fun onItemClick(position: Int, myOrderData: MyOrderData)
    fun onCancelOrderItem(position: Int, myOrderData: MyOrderData)
}
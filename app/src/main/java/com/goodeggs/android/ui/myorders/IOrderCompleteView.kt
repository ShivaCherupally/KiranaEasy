package com.goodeggs.android.ui.myorders

import android.widget.Button
import com.goodeggs.android.model.OrderCompleteInfo

interface IOrderCompleteView {
    fun onItemEditClick(position: Int, orderCompleteInfo: OrderCompleteInfo)

    fun onItemReturnReason(position: Int, orderCompleteInfo: OrderCompleteInfo)
}
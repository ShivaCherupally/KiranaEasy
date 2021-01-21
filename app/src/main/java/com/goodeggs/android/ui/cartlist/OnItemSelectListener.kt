package com.goodeggs.android.ui.cartlist

import android.view.View
import com.goodeggs.android.model.CartData


interface OnItemSelectListener<T> {

    fun onItemSelected(item: CartData, position: Int, view: View)
}
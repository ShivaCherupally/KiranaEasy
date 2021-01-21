package com.goodeggs.android.ui.cartlist

import android.view.View
import com.goodeggs.android.model.CartData


interface OnItemUpdateListener<T> {

    fun onItemUpdated(item: CartData, position: Int, view: View, addingStatus: Boolean)

    fun onItemRemoved(item: T, position: Int, view: View)


}
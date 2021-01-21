package com.goodeggs.android.ui.subcatogory

import android.widget.Button
import com.goodeggs.android.model.ProductData

interface IProductClickListener {
    fun onRecyclerViewItemClick(view: Button, productData: ProductData)
    fun onItemView(position: Int, productData: ProductData)
}
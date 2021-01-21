package com.goodeggs.android.ui.categorysearch

import android.widget.Button
import com.goodeggs.android.model.ProductInfo

interface ISearchItemClickListener {
    fun onRecyclerViewItemClick(view: Button, productInfo: ProductInfo)
}
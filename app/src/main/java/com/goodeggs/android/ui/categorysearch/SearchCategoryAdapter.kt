package com.goodeggs.android.ui.categorysearch

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.goodeggs.android.R
import com.goodeggs.android.databinding.SearchCategoryItemBinding
import com.goodeggs.android.model.ProductInfo

class SearchCategoryAdapter(
        private val movies: List<ProductInfo>,
        private val listener: ISearchItemClickListener) : RecyclerView.Adapter<SearchCategoryAdapter.MoviesViewHolder>() {

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MoviesViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.context),
                            R.layout.search_category_item,
                            parent,
                            false
                    )
            )

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {


        if (movies[position].pic.length > 4) {
            Glide.with(holder.cartItemBinding.productimg.context).load(movies[position].pic).into(holder.cartItemBinding.productimg)
        }

        holder.cartItemBinding.productname.setText(movies[position].product_name)
        holder.cartItemBinding.mrptv.setText("MRP Rs " + movies[position].mrp.toString())
        holder.cartItemBinding.mrptv.paintFlags = holder.cartItemBinding.mrptv.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        holder.cartItemBinding.salepricetv.setText("Sale Rs " + movies[position].sale_price.toString())

        holder.itemView.setOnClickListener {
            listener.onRecyclerViewItemClick(
                    holder.cartItemBinding.addcart,
                    movies[position])
        }

    }


    inner class MoviesViewHolder(
            val cartItemBinding: SearchCategoryItemBinding
    ) : RecyclerView.ViewHolder(cartItemBinding.root)

}
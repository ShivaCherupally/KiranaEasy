package com.goodeggs.android.ui.subcatogory

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.goodeggs.android.R
import com.goodeggs.android.databinding.CartItemBinding
import com.goodeggs.android.model.ProductData
import kotlinx.android.synthetic.main.activity_cart_list.*
import kotlinx.android.synthetic.main.product_info_fragment.*

class ProductAdapter(
        private val movies: List<ProductData>,
        private val listener: IProductClickListener) : RecyclerView.Adapter<ProductAdapter.MoviesViewHolder>() {
    var list_of_items = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30")

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MoviesViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.context),
                            R.layout.cart_item,
                            parent,
                            false
                    )
            )

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {


        if (!movies[position].pic.isNullOrEmpty()) {
            if (movies[position].pic.length > 0) {
                Glide.with(holder.cartItemBinding.productimg.context).load(movies[position].pic).into(holder.cartItemBinding.productimg)
            }
        }

        if (!movies[position].min_qty.toString().isNullOrEmpty()) {
            holder.cartItemBinding.quantity.setText("1" + " Qty")
        }

        if (!movies[position].product_name.isNullOrEmpty()) {
            holder.cartItemBinding.productname.setText(movies[position].product_name)
        }

        if (!movies[position].mrp.isNullOrEmpty()) {
            holder.cartItemBinding.mrptv.setText("MRP Rs " + movies[position].mrp.toString() + "")
            holder.cartItemBinding.mrptv.paintFlags = holder.cartItemBinding.mrptv.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }

        if (!movies[position].sale_price.isNullOrEmpty()) {
            holder.cartItemBinding.salepricetv.setText("Sale Rs " + movies[position].sale_price.toString())
        }
        movies[position].min_qty = 1

        //  //0 instock
        //                    //1 out stock
        if (movies[position].instock == 0) {
            holder.cartItemBinding.addcart.setBackgroundTintList(ContextCompat.getColorStateList(holder.cartItemBinding.addcart.context, R.color.konect_color));
            if (!movies[position].isAdded) {
                holder.cartItemBinding.addcart.text = "ADD TO CART"
            } else {
                holder.cartItemBinding.addcart.text = "ADDDED"
            }
        } else {
            holder.cartItemBinding.addcart.text = "Out of Stock"
            holder.cartItemBinding.addcart.setBackgroundTintList(ContextCompat.getColorStateList(holder.cartItemBinding.addcart.context, R.color.lite_gray))
        }


        holder.cartItemBinding.addcart.setOnClickListener {
            if (!holder.cartItemBinding.addcart.text.toString().equals("Out of Stock")) {
                if (!holder.cartItemBinding.addcart.text.toString().equals("ADDED")) {
                    holder.cartItemBinding.addcart.setText("ADDED")
                    movies[position].isAdded = true
                    listener.onRecyclerViewItemClick(holder.cartItemBinding.addcart,
                            movies[position])
                }
            }
        }
        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(holder.cartItemBinding.quantityspn.context, android.R.layout.simple_spinner_item, list_of_items)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        holder.cartItemBinding.quantityspn.setAdapter(aa)

        holder.cartItemBinding.quantityspn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, positionLocal: Int, Id: Long) {
                movies[position].min_qty = list_of_items[positionLocal].toInt()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        holder.itemView.setOnClickListener {
            listener.onItemView(
                    position,
                    movies[position])
        }

    }


    inner class MoviesViewHolder(val cartItemBinding: CartItemBinding
    ) : RecyclerView.ViewHolder(cartItemBinding.root)

}
package com.goodeggs.android.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.goodeggs.android.R
import com.goodeggs.android.api.RecyclerViewClickListener
import com.goodeggs.android.databinding.CartItemBinding
import com.goodeggs.android.databinding.CartItemsBinding
import com.goodeggs.android.databinding.CategoryIndividualItemBinding
import com.goodeggs.android.databinding.OrderItemGeBinding
import com.goodeggs.android.model.CartData
import com.goodeggs.android.model.CategoryData
import com.goodeggs.android.model.ProductTypesData
import kotlinx.android.synthetic.main.cart_items.view.*

class CartAdapter(
        private val cartData: List<CartData>,
        private val listener: ICartRemoveClick
) : RecyclerView.Adapter<CartAdapter.CartAdapterViewHolder>() {

    override fun getItemCount() = cartData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            CartAdapterViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.context),
                            R.layout.cart_items,
                            parent,
                            false
                    )
            )

    override fun onBindViewHolder(holder: CartAdapterViewHolder, position: Int) {

        holder.cartItemsBinding.productname.text = cartData[position].product_name
        holder.cartItemsBinding.prodcuctqty.text = cartData[position].product_qty.toString()
        holder.cartItemsBinding.packtqty.text = cartData[position].pack_qty.toString()
        holder.cartItemsBinding.deliverydate.text = cartData[position].delivery_date


        /*if (categoryData[position].pic != null) {
            Glide.with(holder.orderItemGeBinding.productoneimg.getContext()).load(categoryData[position].pic).into(holder.orderItemGeBinding.productoneimg)
        }


        holder.orderItemGeBinding.productname.setText(categoryData[position].product_name)



        holder.orderItemGeBinding.productoneadd.setOnClickListener {
            listener.onAddItemClick(
                    holder.orderItemGeBinding.productoneadd,
                    categoryData[position]
            )
        }*/

        holder.cartItemsBinding.removeBtn.setOnClickListener {
            listener.onRemoveItemClick(
                    holder.cartItemsBinding.removeBtn,
                    cartData[position]
            )
        }
    }


    inner class CartAdapterViewHolder(
            val cartItemsBinding: CartItemsBinding
    ) : RecyclerView.ViewHolder(cartItemsBinding.root)

}
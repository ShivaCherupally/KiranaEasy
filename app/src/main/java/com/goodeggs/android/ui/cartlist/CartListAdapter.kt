package com.goodeggs.android.ui.cartlist

import android.content.Context
import android.graphics.Paint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.goodeggs.android.R
import com.goodeggs.android.model.CartData
import com.goodeggs.android.utils.CartCounterView
import com.goodeggs.android.utils.Utility
import kotlinx.android.synthetic.main.cart_items.view.*


class CartListAdapter(context: Context) : BaseRecyclerAdapter<CartData,

        CartListAdapter.CartViewHolder>(context) {

    private val itemList: MutableList<CartData> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(parent.inflate(R.layout.cart_items))
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.onBindItem(itemList[position])
    }

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBindItem(cartItem: CartData) {
            itemView.productname.text = cartItem.product_name
            itemView.prodcuctqty.text = cartItem.product_qty.toString()
            itemView.packtqty.text = cartItem.pack_qty.toString()
            itemView.deliverydate.text = cartItem.delivery_date

            itemView.setOnClickListener {
                listener?.onItemSelected(cartItem, adapterPosition, itemView)
            }

            /*if (!cartItem.pic.isNullOrEmpty()) {
                if (cartItem.pic.length > 3) {
                    Glide.with(itemView.productimg.getContext()).load(cartItem.pic).into(itemView.productimg)
                }
            }*/

            /*itemView.removebtn.setOnClickListener(View.OnClickListener {
                Utility.getAuditionTalantFragment()?.itemRemoved(cartItem.sale_price.toDouble(), itemList.size, cartItem.cart_id, itemList)
                removeItem(adapterPosition)
            })*/

            /*itemView.continueshopping.setOnClickListener(View.OnClickListener {
//                updateItemListener?.onItemUpdated(cartItem, adapterPosition, itemView.continueshopping)
            })*/


            /*itemView.counterView.addCounterValueChangeListener(object : CartCounterView.CounterValueChangeListener {

                override fun onValueDelete(count: Int) {
                    if (count == 0) {
                        Utility.getAuditionTalantFragment()?.itemRemoved(cartItem.mrp.toDouble(), itemList.size, cartItem.cart_id, itemList)
                        removeItem(adapterPosition)
                    } else {
                        cartItem.quantity = count
//                        itemView.mrptv.text = "MRP Rs.${cartItem.sale_price.toDouble() * count}."
                        updateItemListener?.onItemUpdated(cartItem, adapterPosition, itemView, false)

                    }

                }

                override fun onValueAdd(count: Int) {
                    cartItem.quantity = count
//                    itemView.mrptv.text = "MRP Rs.${cartItem.sale_price.toDouble() * count}."
                    updateItemListener?.onItemUpdated(cartItem, adapterPosition, itemView, true)

                }
            })*/
        }
    }

    fun removeItem(position: Int) {
        itemList.removeAt(position)
        notifyDataSetChanged()
    }


    fun addList(newList: List<CartData>) {
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

    /*  fun getCurrentList(cartFinalList: List<CartData>?) {
          cartFinalList.
      }*/

    override fun getItemCount(): Int {
        return itemList.size
    }
}
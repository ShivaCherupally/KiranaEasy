package com.goodeggs.android.ui.myorders

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.goodeggs.android.R
import com.goodeggs.android.databinding.OrderItemBinding
import com.goodeggs.android.model.MyOrderData

class OrderAdapter(
        private val myOrderData: List<MyOrderData>,
        private val listener: IOrderViewClickListener
       ) :
        RecyclerView.Adapter<OrderAdapter.MoviesViewHolder>() {

    override fun getItemCount() = myOrderData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MoviesViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.context),
                            R.layout.order_item,
                            parent,
                            false
                    )
            )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {

//        holder.dealDayItemBinding.sno.setText(position  "")
        holder.dealDayItemBinding.orderId.setText(myOrderData[position].order_unique_id)
        holder.dealDayItemBinding.orderstatus.setText(myOrderData[position].order_status)
        holder.dealDayItemBinding.orderDate.setText(myOrderData[position].order_date)
//        holder.dealDayItemBinding.orderAddress.setText(myOrderData[position].address + "\n" + myOrderData[position].landmark + "\n" + AppPreferences.cityName + "-" + AppPreferences.pincode)
//        holder.dealDayItemBinding.orderTotal.setText("Rs " + myOrderData[position].grandtotal + "")
        //

        if (myOrderData[position].order_status != null) {
            if (myOrderData[position].order_status.equals("Pending")) {
                holder.dealDayItemBinding.cancelorder.visibility = View.VISIBLE
                holder.dealDayItemBinding.bottomLL.visibility = View.VISIBLE
            } else {
                holder.dealDayItemBinding.bottomLL.visibility = View.GONE
                holder.dealDayItemBinding.cancelorder.visibility = View.GONE
            }
        }


        holder.itemView.setOnClickListener {
            listener.onItemClick(
                    position,
                    myOrderData[position])
        }

        holder.dealDayItemBinding.cancelorder.setOnClickListener {
            listener.onCancelOrderItem(
                    position,
                    myOrderData[position])
        }

    }

    inner class MoviesViewHolder(val dealDayItemBinding: OrderItemBinding) : RecyclerView.ViewHolder(dealDayItemBinding.root)


}
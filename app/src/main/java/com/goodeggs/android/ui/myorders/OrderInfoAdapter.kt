package com.goodeggs.android.ui.myorders

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.goodeggs.android.R
import com.goodeggs.android.databinding.OrderInfoFragmentBinding
import com.goodeggs.android.model.OrderCompleteInfo

class OrderInfoAdapter(
        private val myOrderData: List<OrderCompleteInfo>,
        private val listener: IOrderCompleteView) :
        RecyclerView.Adapter<OrderInfoAdapter.MoviesViewHolder>() {
    var list_of_products = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30")

    var listofPacks = arrayOf("6", "12", "30")
    override fun getItemCount() = myOrderData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MoviesViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.context),
                            R.layout.order_info_fragment,
                            parent,
                            false
                    )
            )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {

        holder.orderInfoFragmentBinding.productname.text = myOrderData[position].product_name

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(holder.orderInfoFragmentBinding.productonespn.context, R.layout.spinner_item_new, list_of_products)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        holder.orderInfoFragmentBinding.productonespn.setAdapter(aa)

        val aa2 = ArrayAdapter(holder.orderInfoFragmentBinding.packonespn.context, R.layout.spinner_item_new, listofPacks)
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        holder.orderInfoFragmentBinding.packonespn.setAdapter(aa2)




        holder.orderInfoFragmentBinding.productonespn.setSelection(myOrderData[position].product_qty - 1)


        if (myOrderData[position].pack_qty == 6) {
            holder.orderInfoFragmentBinding.packonespn.setSelection(0)
        } else if (myOrderData[position].pack_qty == 12) {
            holder.orderInfoFragmentBinding.packonespn.setSelection(1)
        } else if (myOrderData[position].pack_qty == 30) {
            holder.orderInfoFragmentBinding.packonespn.setSelection(2)
        }

        holder.orderInfoFragmentBinding.packonespn.isEnabled = false
        holder.orderInfoFragmentBinding.productonespn.isEnabled = false

        if (myOrderData[position].orderedit) {
            holder.orderInfoFragmentBinding.editBtn.visibility = View.VISIBLE
        } else {
            holder.orderInfoFragmentBinding.editBtn.visibility = View.GONE
        }



        holder.orderInfoFragmentBinding.productonespn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, positionLocal: Int, Id: Long) {
                myOrderData[position].product_qty = list_of_products[positionLocal].toInt()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        holder.orderInfoFragmentBinding.packonespn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, positionLocal: Int, Id: Long) {
                myOrderData[position].pack_qty = listofPacks[positionLocal].toInt()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        holder.orderInfoFragmentBinding.editBtn.setOnClickListener {
            holder.orderInfoFragmentBinding.editBtn.visibility = View.GONE
            holder.orderInfoFragmentBinding.updateOrderBtn.visibility = View.VISIBLE


            holder.orderInfoFragmentBinding.packonespn.isEnabled = true
            holder.orderInfoFragmentBinding.productonespn.isEnabled = true

        }

        holder.orderInfoFragmentBinding.updateOrderBtn.setOnClickListener {

            listener.onItemEditClick(
                    position,
                    myOrderData[position])


            holder.orderInfoFragmentBinding.packonespn.isEnabled = false
            holder.orderInfoFragmentBinding.productonespn.isEnabled = false

            holder.orderInfoFragmentBinding.editBtn.visibility = View.VISIBLE
            holder.orderInfoFragmentBinding.updateOrderBtn.visibility = View.GONE
        }

        holder.orderInfoFragmentBinding.returnBtn.setOnClickListener {
            listener.onItemReturnReason(
                    position,
                    myOrderData[position])

        }

    }

    inner class MoviesViewHolder(val orderInfoFragmentBinding: OrderInfoFragmentBinding) : RecyclerView.ViewHolder(orderInfoFragmentBinding.root)

}
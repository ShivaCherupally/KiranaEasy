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
import com.goodeggs.android.databinding.OrderItemGeBinding
import com.goodeggs.android.model.ProductTypesData

class CategoryAdapter(
        private val categoryData: List<ProductTypesData>,
        private val listener: IProductAddClick
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    var list_of_items = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30")

    var listofPacks: ArrayList<String> = ArrayList()
    override fun getItemCount() = categoryData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            CategoryViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.context),
                            R.layout.order_item_ge,
                            parent,
                            false
                    )
            )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        if (categoryData[position].pic != null) {
            Glide.with(holder.orderItemGeBinding.productoneimg.getContext()).load(categoryData[position].pic).into(holder.orderItemGeBinding.productoneimg)
        }


        holder.orderItemGeBinding.productname.setText(categoryData[position].product_name)

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(holder.orderItemGeBinding.productonespn.context, R.layout.spinner_style, list_of_items)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        holder.orderItemGeBinding.productonespn.setAdapter(aa)

        listofPacks = ArrayList()
        for (i in 0 until categoryData[position].pack_qty.size) {
            listofPacks.add(categoryData[position].pack_qty[i].pack_qty)
        }

        val aa2 = ArrayAdapter(holder.orderItemGeBinding.packonespn.context, R.layout.spinner_style, listofPacks)
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        holder.orderItemGeBinding.packonespn.setAdapter(aa2)

        holder.orderItemGeBinding.productonespn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, positionLocal: Int, Id: Long) {
                categoryData[position].product_qty = list_of_items[positionLocal].toInt()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        holder.orderItemGeBinding.packonespn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, positionLocal: Int, Id: Long) {
                categoryData[position].packqty = listofPacks[positionLocal].toInt()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        holder.orderItemGeBinding.productoneadd.setOnClickListener {
            listener.onAddItemClick(
                    holder.orderItemGeBinding.productoneadd,
                    categoryData[position]
            )
        }
    }


    inner class CategoryViewHolder(
            val orderItemGeBinding: OrderItemGeBinding
    ) : RecyclerView.ViewHolder(orderItemGeBinding.root)

}
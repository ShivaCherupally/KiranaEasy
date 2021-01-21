package com.goodeggs.android.ui.deals

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.goodeggs.android.R
import com.goodeggs.android.databinding.DealDayItemBinding
import com.goodeggs.android.model.DealData

class DealAdapter(
        private val deals: List<DealData>,
        private val listener: IDealViewClickListener

) : RecyclerView.Adapter<DealAdapter.MoviesViewHolder>() {

    override fun getItemCount() = deals.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MoviesViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.context),
                            R.layout.deal_day_item,
                            parent,
                            false
                    )
            )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.dealDayItemBinding.dealsday = deals[position]

        Glide.with(holder.dealDayItemBinding.productimg.getContext()).load(deals[position].pic).into(holder.dealDayItemBinding.productimg)

        holder.dealDayItemBinding.itempricetv.setText("Rs." + deals[position].sale_price)

        holder.dealDayItemBinding.productimg.setOnClickListener {
            listener.onItemDealsClick(
                    position,
                    deals[position]
            )
        }
    }


    inner class MoviesViewHolder(
            val dealDayItemBinding: DealDayItemBinding
    ) : RecyclerView.ViewHolder(dealDayItemBinding.root)

}
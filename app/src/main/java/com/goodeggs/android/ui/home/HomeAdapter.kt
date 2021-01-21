package com.goodeggs.android.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.goodeggs.android.R
import com.goodeggs.android.api.RecyclerViewClickListener
import com.goodeggs.android.databinding.DealItemBinding
import com.goodeggs.android.model.DealData


class HomeAdapter(
        private val deals: List<DealData>) : RecyclerView.Adapter<HomeAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MoviesViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.context),
                            R.layout.deal_item,
                            parent,
                            false
                    )
            )

    //    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.searchItemBinding.deals = deals[position]

//        Glide.with(holder.searchItemBinding.productimg.context).load(deals[position].pic).into(holder.searchItemBinding.productimg)

        if (deals[position].pic != null && deals[position].pic?.length!! > 4) {
            Glide.with(holder.searchItemBinding.productimg.getContext()).load(deals[position].pic).into(holder.searchItemBinding.productimg)
        }

        holder.searchItemBinding.itempricetv.setText("Rs." + deals[position].sale_price.toString() + "")

       /* holder.searchItemBinding.productimg.setOnClickListener {
            listener.onRecyclerViewItemDealsClick(
                    holder.searchItemBinding.productimg,
                    deals[position]
            )
        }*/
        /*holder.itemView.setOnClickListener {
            listener.onRecyclerViewItemDealsClick(
                    holder.searchItemBinding.productimg,
                    deals[position]
            )
        }*/
    }


    inner class MoviesViewHolder(
            val searchItemBinding: DealItemBinding
    ) : RecyclerView.ViewHolder(searchItemBinding.root)

    override fun getItemCount() = deals.size

}
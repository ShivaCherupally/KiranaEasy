package com.goodeggs.android.ui.subcategory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.goodeggs.android.R
import com.goodeggs.android.databinding.CategoryIndividualItemBinding
import com.goodeggs.android.model.SubCategoryInfo

class SubCategoryItemAdapter(
        private val categoryData: List<SubCategoryInfo>,
        private val listener: ISubCategoryItemClick
) : RecyclerView.Adapter<SubCategoryItemAdapter.CategoryViewHolder>() {

    override fun getItemCount() = categoryData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            CategoryViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.context),
                            R.layout.category_individual_item,
                            parent,
                            false
                    )
            )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        if (categoryData[position].pic.length > 4) {
            Glide.with(holder.searchItemBinding.categoryimg.getContext()).load(categoryData[position].pic).into(holder.searchItemBinding.categoryimg)
        }

        holder.searchItemBinding.tvItem.setText(categoryData[position].subcategory_title)

        holder.searchItemBinding.categoryimg.setOnClickListener {
            listener.onItemView(position,
                    categoryData[position]
            )
        }
    }


    inner class CategoryViewHolder(
            val searchItemBinding: CategoryIndividualItemBinding
    ) : RecyclerView.ViewHolder(searchItemBinding.root)

}
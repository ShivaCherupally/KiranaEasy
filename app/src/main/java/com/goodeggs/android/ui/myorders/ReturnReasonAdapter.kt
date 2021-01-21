package com.goodeggs.android.ui.myorders

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.goodeggs.android.R
import com.goodeggs.android.databinding.ItemCommentsReportOptionBinding
import com.goodeggs.android.model.ReturnReasonData
import com.goodeggs.android.ui.dailog.IReturnReasonDialog

class ReturnReasonAdapter(
        private val myOrderData: List<ReturnReasonData>,
        private var selectedIndex: Int? = -1
) :
        RecyclerView.Adapter<ReturnReasonAdapter.MoviesViewHolder>() {

    override fun getItemCount() = myOrderData.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MoviesViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.context),
                            R.layout.item_comments_report_option,
                            parent,
                            false
                    )
            )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {

//        holder.dealDayItemBinding.sno.setText(position  "")
        holder.dealDayItemBinding.tvUserName.setText(myOrderData[position].rt_title)
        if (selectedIndex!! > -1 && selectedIndex == position) {
            holder.dealDayItemBinding.ivRadioBtn.setImageResource(R.drawable.ic_radio_btn_on);
        } else {
            holder.dealDayItemBinding.ivRadioBtn.setImageResource(R.drawable.ic_radio_btn_off);
        }
        holder.itemView.setOnClickListener(View.OnClickListener {
            changeSelection(position)
        })

        /* holder.itemView.setOnClickListener {
             listener.onItemClick(
                     position,
                     myOrderData[position])
         }

         holder.dealDayItemBinding.cancelorder.setOnClickListener {
             listener.onCancelOrderItem(
                     position,
                     myOrderData[position])
         }*/


    }

    private fun changeSelection(position: Int) {
        selectedIndex = position
        notifyDataSetChanged()
    }

    fun getSelectedIndex(): Int? {
        return selectedIndex
    }

    inner class MoviesViewHolder(val dealDayItemBinding: ItemCommentsReportOptionBinding) : RecyclerView.ViewHolder(dealDayItemBinding.root)


}
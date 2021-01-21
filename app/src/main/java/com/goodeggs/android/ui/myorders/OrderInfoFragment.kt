package com.goodeggs.android.ui.myorders

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.goodeggs.android.MainViewModel
import com.goodeggs.android.R
import com.goodeggs.android.model.CartData
import com.goodeggs.android.model.OrderCompleteInfo
import com.goodeggs.android.model.ReturnReasonData
import com.goodeggs.android.ui.CommonSkeletonAdapter
import com.goodeggs.android.ui.EmptyDataAdapter
import com.goodeggs.android.ui.RController
import com.goodeggs.android.ui.dailog.IUpdateAppDialog
import com.goodeggs.android.ui.dailog.ReturnDailog
import com.goodeggs.android.ui.login.LoginActivity
import com.goodeggs.android.utils.Utility
import kotlinx.android.synthetic.main.change_paasword_frg.*
import kotlinx.android.synthetic.main.my_order_list.*
import kotlinx.android.synthetic.main.my_order_list.loading

class OrderInfoFragment : Fragment(), IOrderCompleteView, IUpdateAppDialog {

    private lateinit var viewModel: MainViewModel
    var updateAppDialog: ReturnDailog? = null

    //    private val orderCompleteInfo: List<OrderCompleteInfo>? = null
    val orderCompleteInfoData: MutableList<OrderCompleteInfo> = arrayListOf()

    //    var context: Context = null
    var iUpdateAppDialog: IUpdateAppDialog? = null


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_order_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        context = getContext()
        iUpdateAppDialog = this
        getOrderInfolist()

    }

    private fun getOrderInfolist() {
        orderCompleteInfoData.clear()
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = CommonSkeletonAdapter(RController.LOADING)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.orderInfoReq(requireArguments().getString("OrderId"))?.observe(requireActivity(), Observer { productInfo ->

            if (productInfo != null && productInfo.isNotEmpty() && productInfo.get(0).success == 1) {
                orderCompleteInfoData.addAll(productInfo)
                recyclerView.removeAllViews()
                recyclerView.isNestedScrollingEnabled = true
                recyclerView.setHasFixedSize(true)
                recyclerView.adapter = OrderInfoAdapter(productInfo, this)
            } else {
                recyclerView.adapter = EmptyDataAdapter(activity, "Product info not available", "", R.drawable.ic_shopping_cart, 6)
            }
        })
    }


    override fun onItemEditClick(position: Int, orderCompleteInfo: OrderCompleteInfo) {
        loading.visibility = View.VISIBLE
        val viewModel: MainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        viewModel.editOrderInfo(orderCompleteInfo.op_id,
                orderCompleteInfo.product_qty,
                orderCompleteInfo.pack_qty).observe(requireActivity(), Observer { userInfo ->
            if (userInfo.success == 1) {
                loading.visibility = View.GONE
                Toast.makeText(context, "Order updated successfully ", Toast.LENGTH_SHORT).show()
                getOrderInfolist()
            } else {
                loading.visibility = View.GONE
                Toast.makeText(context, "Failed to update order", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onItemReturnReason(position: Int, orderCompleteInfo: OrderCompleteInfo) {

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getReturnReasons().observe(viewLifecycleOwner, Observer { movies ->
            if (movies.size != 0 && !movies.isNullOrEmpty()) {
                setResonInfoPop(movies, position)
            }
        })

    }

    private fun setResonInfoPop(movies: List<ReturnReasonData>?, position: Int) {
        updateAppDialog = ReturnDailog(context, iUpdateAppDialog, movies, position)
        updateAppDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        updateAppDialog!!.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        updateAppDialog!!.show()
    }

    override fun updateNow() {
    }

    override fun doLater() {
    }

    override fun reasonReturn(reason: String?, position: Int) {
        loading.visibility = View.VISIBLE
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.sendReturnReason(orderCompleteInfoData.get(position).op_id,
                orderCompleteInfoData.get(position).product_id,
                orderCompleteInfoData.get(position).product_qty.toString(),
                orderCompleteInfoData.get(position).pack_qty.toString(),
                reason)?.observe(requireActivity(), Observer { userInfo ->
            if (userInfo?.message.equals("Success")) {
                loading.visibility = View.GONE
                Toast.makeText(context, "Successfully return", Toast.LENGTH_SHORT).show()
                getOrderInfolist()
            } else {
                loading.visibility = View.GONE
                Toast.makeText(activity, "Failed to return", Toast.LENGTH_SHORT).show()
            }
        })
    }

}

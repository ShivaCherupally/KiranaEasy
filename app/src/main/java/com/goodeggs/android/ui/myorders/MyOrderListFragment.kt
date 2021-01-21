package com.goodeggs.android.ui.myorders

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.goodeggs.android.MainViewModel
import com.goodeggs.android.R
import com.goodeggs.android.bottommenu.HelperActivity
import com.goodeggs.android.model.MyOrderData
import com.goodeggs.android.ui.CommonSkeletonAdapter
import com.goodeggs.android.ui.EmptyDataAdapter
import com.goodeggs.android.ui.RController
import com.goodeggs.android.utils.Constants
import kotlinx.android.synthetic.main.common_recyclerview_frg.*


class MyOrderListFragment : Fragment(), IOrderViewClickListener {

    private lateinit var viewModel: MainViewModel
    private var promoDialog: Dialog? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.common_recyclerview_frg, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loaddata()
    }


    fun loaddata() {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = CommonSkeletonAdapter(RController.LOADING)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        getOrderList()

    }

    private fun getOrderList() {
        viewModel.getOrderList().observe(viewLifecycleOwner, Observer { movies ->
            if (movies != null && movies.isNotEmpty() && movies.get(0).success == 1) {
                recyclerView.removeAllViews()
                recyclerView.isNestedScrollingEnabled = true
                recyclerView.setHasFixedSize(true)
                recyclerView.adapter = OrderAdapter(movies, this)
            } else {
                recyclerView.adapter = EmptyDataAdapter(activity, "There are no orders yet", "", R.drawable.ic_shopping_cart, 6)
            }
        })
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance(param1: String?, param2: String?): MyOrderListFragment {
            val fragment = MyOrderListFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onItemClick(position: Int, myOrderData: MyOrderData) {
        val intent = Intent(requireContext(), HelperActivity::class.java)
        intent.putExtra(Constants.FRAGMENT_TITLE, "Order Details")
        intent.putExtra(Constants.FRAGMENT_KEY, 8014)
        intent.putExtra("OrderId", myOrderData.order_id)
        startActivity(intent)
    }

    override fun onCancelOrderItem(position: Int, myOrderData: MyOrderData) {
        loading.visibility = View.VISIBLE
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.cancelOrder(myOrderData.order_id)?.observe(requireActivity(), Observer { userInfo ->
            loading.visibility = View.GONE
            if (userInfo.get(0).success == 1) {
                Toast.makeText(activity, "Your order has been cancelled", Toast.LENGTH_LONG).show()
                recyclerView.removeAllViews()
                loaddata()
            } else {
                Toast.makeText(activity, "Fail to cancel order", Toast.LENGTH_SHORT).show()
            }
        })
    }

   /* fun deleteConfm(pos: Int, condition: String?) {
        promoDialog = Dialog(context)
        promoDialog!!.getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        promoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        promoDialog.setCancelable(false)
        promoDialog.setContentView(R.layout.are_you_sure_exit_popup)
        val description_title = promoDialog.findViewById(R.id.description_title) as TextView
        val nobtn = promoDialog.findViewById(R.id.nobtn) as Button
        val yesBtn = promoDialog.findViewById(R.id.yesBtn) as Button
        promoDialog.show()
        description_title.text = "Are you sure you want to delete ?"
        nobtn.setOnClickListener { promoDialog.dismiss() }
        yesBtn.setOnClickListener {
            promoDialog.dismiss()
            iMultipleSelectionAdapter.deleteMultipleSelectionItem(pos, condition)
        }
    }*/


}

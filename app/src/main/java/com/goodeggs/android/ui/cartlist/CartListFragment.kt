package com.goodeggs.android.ui.cartlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.goodeggs.android.MainViewModel
import com.goodeggs.android.R
import com.goodeggs.android.bottommenu.HelperActivity
import com.goodeggs.android.model.CartData
import com.goodeggs.android.ui.CommonSkeletonAdapter
import com.goodeggs.android.ui.EmptyDataAdapter
import com.goodeggs.android.ui.RController
import com.goodeggs.android.ui.home.CartAdapter
import com.goodeggs.android.ui.home.ICartRemoveClick
import com.goodeggs.android.utils.AppPreferences
import com.goodeggs.android.utils.Common
import com.goodeggs.android.utils.Constants
import com.goodeggs.android.utils.Utility
import kotlinx.android.synthetic.main.activity_cart_list.*
import kotlinx.android.synthetic.main.activity_cart_list.loading
import kotlinx.android.synthetic.main.activity_cart_list.mycartlabel
import kotlinx.android.synthetic.main.activity_cart_list.mycartlabelRl
import kotlinx.android.synthetic.main.home_fragment.*
import org.json.JSONArray


class CartListFragment : Fragment(), ICartRemoveClick {

    private lateinit var viewModel: MainViewModel

    val itemList: MutableList<CartData> = arrayListOf()
    var totalCart: Double? = 0.0

    val finalCartList: MutableList<CartData> = arrayListOf()
    val orderIdsList = JSONArray()

    fun CartListFragment() {
        // Required empty public constructor
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_cart_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Utility.setAuditionTalantFragment(this)

        header.visibility = View.GONE
        mycartlabel.visibility = View.GONE
        proceedbtn.visibility = View.GONE

        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerViewCart.layoutManager = linearLayoutManager
        recyclerViewCart.adapter = CommonSkeletonAdapter(RController.LOADING)

        getCartListData()
        proceedbtn.setOnClickListener(View.OnClickListener {

            loading.visibility = View.VISIBLE
            for (i in 0 until itemList.size) {
                val cartId = itemList.get(i).cart_id
                Log.v("cartId", cartId + i)
                orderIdsList.put(cartId)
            }

            var ordersall = orderIdsList.toString()
            ordersall = ordersall.replace("\\[".toRegex(), "").replace("\\]".toRegex(), "")
            ordersall = ordersall.replace("\"", "")
            viewModel.orderCartReq(AppPreferences.userId, ordersall)?.observe(requireActivity(),
                    Observer { userInfo ->
                        loading.visibility = View.GONE
                        if (userInfo?.message != null) {
                            if (userInfo.message.equals("Success")) {
                                Toast.makeText(context, "Ordered successfully ", Toast.LENGTH_SHORT).show()
                                val intent = Intent(requireContext(), HelperActivity::class.java)
                                intent.putExtra(Constants.FRAGMENT_TITLE, "Address")
                                intent.putExtra(Constants.FRAGMENT_KEY, 8007)
                                if (userInfo.order_id != null) {
                                    intent.putExtra("OrderId", userInfo.order_id)
                                    startActivity(intent)
                                } else {
                                    Utility.navigateScreens(requireContext(), "Order Failed", 8012)
                                    Toast.makeText(context, "Failed to order", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                Utility.navigateScreens(requireContext(), "Order Failed", 8012)
                                Toast.makeText(context, "Failed to order", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Utility.navigateScreens(requireContext(), "Order Failed", 8012)
                            Toast.makeText(context, "Failed to order", Toast.LENGTH_SHORT).show()
                        }
                    })
        })
    }


    fun getCartListData() {
        itemList.clear()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getCartList(AppPreferences.userId).observe(viewLifecycleOwner, Observer { cartItems ->
            recyclerViewCart.removeAllViews()
            if (cartItems.isNotEmpty() && !cartItems.isNullOrEmpty() && cartItems.get(0).success == 1) {
                itemList.addAll(cartItems)
                mycartlabelRl.visibility = View.VISIBLE
                recyclerViewCart.adapter = CartAdapter(cartItems, this)
                mycartlabel.text = "My Cart "
                proceedbtn.visibility = View.VISIBLE


                AppPreferences.cartCount = cartItems.size.toString()

            } else {
                recyclerViewCart.adapter = EmptyDataAdapter(activity, "", "No Cart Data",
                        R.drawable.ic_list_interface_symbol, 6)
                proceedbtn.visibility = View.GONE
                mycartlabelRl.visibility = View.GONE
            }
        })
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance(param1: String?, param2: String?): CartListFragment {
            val fragment = CartListFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onRemoveItemClick(view: View, categoryData: CartData) {
        loading.visibility = View.VISIBLE
        val viewModel: MainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        viewModel.removeCartReq(categoryData.cart_id)?.observe(requireActivity(), Observer { userInfo ->
            if (userInfo?.message.equals("Success")) {
                loading.visibility = View.GONE
                Toast.makeText(requireContext(), "successfully removed from cart", Toast.LENGTH_SHORT).show()
                getCartListData()
            } else {
                loading.visibility = View.GONE
                Toast.makeText(requireContext(), "Failed to remove from cart", Toast.LENGTH_SHORT).show()
            }
        })
    }


}

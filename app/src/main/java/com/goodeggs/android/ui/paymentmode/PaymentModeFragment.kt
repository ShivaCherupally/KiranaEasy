package com.goodeggs.android.ui.paymentmode

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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.goodeggs.android.utils.Constants
import com.goodeggs.android.MainViewModel
import com.goodeggs.android.R
import com.goodeggs.android.api.RecyclerViewClickListener
import com.goodeggs.android.bottommenu.HelperActivity
import com.goodeggs.android.model.CartData
import com.goodeggs.android.model.CategoryData
import com.goodeggs.android.model.DealData
import com.goodeggs.android.utils.AppPreferences
import com.goodeggs.android.utils.Utility
import kotlinx.android.synthetic.main.activity_paymentmethod.*
import org.json.JSONArray


class PaymentModeFragment : Fragment(), RecyclerViewClickListener {
    private lateinit var viewModel: MainViewModel

    val orderIdsList = JSONArray()
    var totalDeliveryPrice: Double? = null
    var orderArrayList = ArrayList<String>();

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_paymentmethod, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getAvailableData()

        proceedbtn.setOnClickListener {
            loading.visibility = View.VISIBLE
            viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

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

        }


    }

    fun getAvailableData() {
        if (requireArguments().getString("CartData") != null) {
            val json: String? = requireArguments().getString("CartData")
            val token: TypeToken<ArrayList<CartData>> = object : TypeToken<ArrayList<CartData>>() {}
            val cartData: ArrayList<CartData> = Gson().fromJson(json, token.type)
            var totalItemsAmount: Double? = 0.0

            for (i in 0 until cartData.size) {
                val cartId = cartData.get(i).cart_id
                Log.v("cartId", cartId + i)
                orderIdsList.put(cartId)
//                totalItemsAmount = totalItemsAmount?.plus(cartData.get(i).sale_price.toDouble() * cartData.get(i).quantity)
                orderArrayList.add(cartId)
            }

            Log.e("jsonArrayStr", orderIdsList.toString() + "")


            itemscount.setText("Price (" + cartData.size.toString() + " items)")
            itemstotalprice.setText("Rs " + totalItemsAmount.toString())


            val deliveryCharges: String? = requireArguments().getString("DeliveryCharges")

            if (deliveryCharges != null && !deliveryCharges.equals("Free")) {
                totalDeliveryPrice = totalItemsAmount?.plus(deliveryCharges.toDouble())
            } else {
                totalDeliveryPrice = totalItemsAmount
                deliveryamount.setText("Free")
            }

            totalamount.setText("Rs " + totalDeliveryPrice.toString())
            totalamountbottom.setText("Rs " + totalDeliveryPrice.toString())


        }

    }

    override fun onRecyclerViewItemClick(view: View, categoryData: CategoryData) {
        when (view.id) {
            /*R.id.button_book -> {
                Toast.makeText(requireContext(), "Book Button Clicked",Toast.LENGTH_LONG).show()
            }
            R.id.layout_like ->{
                Toast.makeText(requireContext(), "Like Layout Clicked",Toast.LENGTH_LONG).show()
            }*/
        }
    }

    override fun onRecyclerViewItemDealsClick(view: View, dealData: DealData) {
    }

    /* override fun onRecyclerViewItemClick(view: View, movie: Movie) {
     }*/
    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance(param1: String?, param2: String?): PaymentModeFragment {
            val fragment = PaymentModeFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }


}

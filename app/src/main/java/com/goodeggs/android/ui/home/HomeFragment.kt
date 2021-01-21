package com.goodeggs.android.ui.home

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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.goodeggs.android.MainViewModel
import com.goodeggs.android.R
import com.goodeggs.android.bottommenu.BottomMenuNew
import com.goodeggs.android.bottommenu.HelperActivity
import com.goodeggs.android.model.CartData
import com.goodeggs.android.model.ProductTypesData
import com.goodeggs.android.ui.CommonSkeletonAdapter
import com.goodeggs.android.ui.EmptyDataAdapter
import com.goodeggs.android.ui.RController
import com.goodeggs.android.ui.cartlist.CartListAdapter
import com.goodeggs.android.ui.cartlist.OnItemUpdateListener
import com.goodeggs.android.utils.AppPreferences
import com.goodeggs.android.utils.Common
import com.goodeggs.android.utils.Constants
import com.goodeggs.android.utils.Utility
import kotlinx.android.synthetic.main.activity_paymentmethod.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.home_fragment.loading
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray


class HomeFragment : Fragment(), IProductAddClick, ICartRemoveClick {

    private lateinit var viewModel: MainViewModel
    val orderIdsList = JSONArray()

    private var instance: HomeFragment? = null
    val itemList: MutableList<CartData> = arrayListOf()

    fun getInstance(): HomeFragment? {
        return instance
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        instance = this
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Common.setHomeFrag(this)

        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
//        addedListRecy.layoutManager = linearLayoutManager
//        addedListRecy.adapter = CommonSkeletonAdapter(RController.LOADING)


        categorylistRecycler.setHasFixedSize(true);
        categorylistRecycler.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL))
        categorylistRecycler.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        categorylistRecycler.layoutManager = GridLayoutManager(requireContext(), 2);
        categorylistRecycler.removeItemDecorationsLine()
        categorylistRecycler.adapter = CommonSkeletonAdapter(RController.LOADING)
        loaddata()
    }


    fun loaddata() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getHolidays().observe(viewLifecycleOwner, Observer { movies ->
            if (movies.size != 0 && !movies.isNullOrEmpty()) {
                categorylistRecycler.adapter = CategoryAdapter(movies, this)
                AppPreferences.cartCount = movies.get(0).cart_count.toString()
                Common.getBottomMenuNew()?.updatedCartCount(movies.get(0).cart_count)
            } else {
                AppPreferences.cartCount = "0"
                categorylistRecycler.adapter = EmptyDataAdapter(activity, "", "No Categories available",
                        R.drawable.ic_list_interface_symbol, 6)
            }
        })
//        getCartListData()
    }

    fun RecyclerView.removeItemDecorationsLine() {
        while (this.itemDecorationCount > 0) {
            this.removeItemDecorationAt(0)
        }
    }

    override fun onAddItemClick(view: View, productData: ProductTypesData) {
        loading.visibility = View.VISIBLE
        val viewModel: MainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.addCartProduct(AppPreferences.userId,
                productData.product_id,
                productData.product_name,
                productData.packqty,
                productData.product_qty).observe(requireActivity(), Observer { userInfo ->
            if (userInfo?.message.equals("Success")) {

                AppPreferences.cartCount = userInfo.cart_count.toString()
                Common.getBottomMenuNew()?.updatedCartCount(userInfo.cart_count)

                loading.visibility = View.GONE
                Toast.makeText(requireContext(), "successfully added to cart", Toast.LENGTH_SHORT).show()
//                getCartListData()
            } else {
                loading.visibility = View.GONE
                Toast.makeText(requireContext(), "Failed to add to cart", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getCartListData() {
        itemList.clear()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getCartList(AppPreferences.userId).observe(viewLifecycleOwner, Observer { cartItems ->
            addedListRecy.removeAllViews()
            if (cartItems.isNotEmpty() && !cartItems.isNullOrEmpty() && cartItems.get(0).success == 1) {
                itemList.addAll(cartItems)
                mycartlabelRl.visibility = View.VISIBLE
                addedListRecy.adapter = CartAdapter(cartItems, this)
                mycartlabel.text = "My Cart "

            } else {
                mycartlabelRl.visibility = View.GONE
            }
        })

        ordernow.setOnClickListener(View.OnClickListener {

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

    override fun onRemoveItemClick(view: View, categoryData: CartData) {
        loading.visibility = View.VISIBLE
        val viewModel: MainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        viewModel.removeCartReq(categoryData.cart_id)?.observe(requireActivity(), Observer { userInfo ->
            if (userInfo?.message.equals("Success")) {
                loading.visibility = View.GONE
                Toast.makeText(requireContext(), "successfully removed from cart", Toast.LENGTH_SHORT).show()
//                getCartListData()
            } else {
                loading.visibility = View.GONE
                Toast.makeText(requireContext(), "Failed to remove from cart", Toast.LENGTH_SHORT).show()
            }
        })
    }

}

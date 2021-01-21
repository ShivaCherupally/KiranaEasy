package com.goodeggs.android.ui.subcatogory

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.goodeggs.android.utils.Constants
import com.goodeggs.android.MainViewModel
import com.goodeggs.android.R
import com.goodeggs.android.bottommenu.HelperActivity
import com.goodeggs.android.model.ProductData
import com.goodeggs.android.ui.CommonSkeletonAdapter
import com.goodeggs.android.ui.EmptyDataAdapter
import com.goodeggs.android.ui.RController
import com.goodeggs.android.utils.AppPreferences
import com.goodeggs.android.utils.Common
import kotlinx.android.synthetic.main.common_recycler.*


class ProductListFragment : Fragment(), IProductClickListener {

//    private lateinit var viewModel: MainViewModel
//    var recyclerView: RecyclerView? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.common_recycler, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loaddata()
    }


    fun loaddata() {

        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerViewCommon.layoutManager = linearLayoutManager
        recyclerViewCommon.adapter = CommonSkeletonAdapter(RController.LOADING)

        var categoryId: String? = requireArguments().getString("CategoryId")
        var subcategory_id: String? = requireArguments().getString("subcategory_id")

        val viewModel: MainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.getProductListVM(categoryId, subcategory_id).observe(viewLifecycleOwner, Observer { movies ->
            recyclerViewCommon.removeAllViews()
            if (movies.size != 0 && !movies.isNullOrEmpty() && movies.get(0).success == 1) {
                Log.v("response", movies.toString() + "");
                recyclerViewCommon.adapter = ProductAdapter(movies, this)
            } else {
                recyclerViewCommon.adapter = EmptyDataAdapter(activity, "", "No Products available",
                        R.drawable.ic_list_interface_symbol, 6)

            }

        })

    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance(param1: String?, param2: String?): ProductListFragment {
            val fragment = ProductListFragment()
            val args = Bundle()

            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onRecyclerViewItemClick(view: Button, productData: ProductData) {
        when (view.id) {
            R.id.addcart -> {
                loading.visibility = View.VISIBLE
                val viewModel: MainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

                viewModel.addCartProduct(AppPreferences.userId,
                        productData.product_id,
                        productData.product_name,
                        productData.min_qty,
                        productData.sale_price.toInt()).observe(requireActivity(), Observer { userInfo ->
                    if (userInfo?.message.equals("Success")) {
                        loading.visibility = View.GONE
                        Toast.makeText(requireContext(), "successfully added to cart", Toast.LENGTH_SHORT).show()
                        cartCountApi()
                    } else {
                        loading.visibility = View.GONE
                        Toast.makeText(requireContext(), "Failed to add to cart", Toast.LENGTH_SHORT).show()
                    }
                })

            }


        }
    }

    fun cartCountApi() {
        Common.getHelperActivity()?.getCartListCount()
    }

    override fun onItemView(position: Int, productData: ProductData) {
        val intent = Intent(requireContext(), HelperActivity::class.java)
        intent.putExtra(Constants.FRAGMENT_TITLE, "Product Details")
        intent.putExtra(Constants.FRAGMENT_KEY, 8010)
        intent.putExtra("ProductId", productData.product_id)
        startActivity(intent)
    }


}

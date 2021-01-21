package com.goodeggs.android.ui.product

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.goodeggs.android.MainViewModel
import com.goodeggs.android.R
import com.goodeggs.android.model.ProductCompleteInfo
import com.goodeggs.android.utils.AppPreferences
import com.goodeggs.android.utils.Common
import kotlinx.android.synthetic.main.product_info_fragment.*

class ProductInfoFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    val productDetailedInfo: MutableList<ProductCompleteInfo> = arrayListOf()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        loading.visibility = View.VISIBLE
        viewModel.productInfoReq(requireArguments().getString("ProductId"))?.observe(requireActivity(), Observer { productInfo ->
            loading.visibility = View.GONE
            Glide.with(requireContext()).load(productInfo.get(0).pic).into(productimg)
            brandname.text = productInfo.get(0).brand_name.toString()
            productname.text = productInfo.get(0).product_name

            mrptv.text = "MRP Rs " + productInfo.get(0).mrp
            mrptv.paintFlags = mrptv.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            salepricetv.text = "Sale Rs " + productInfo.get(0).sale_price

            if (productInfo.get(0).instock == 1) {
                addcart.text = "Out of Stock"
            }


            productDetailedInfo.addAll(productInfo)

        })




        addcart.setOnClickListener {

            if (!addcart.text.toString().equals("Out of Stock")) {
                if (!addcart.text.toString().equals("Added")) {
                    viewModel.addCartProduct(AppPreferences.userId,
                            requireArguments().getString("ProductId"),
                            productDetailedInfo.get(0).product_name,
                            1,
                            productDetailedInfo.get(0).sale_price.toInt()).observe(requireActivity(), Observer { userInfo ->
                        if (userInfo?.message.equals("Success")) {
                            addcart.text = "Added"
                            Toast.makeText(context, "successfully added to cart", Toast.LENGTH_SHORT).show()
                            cartApiCall()
                        } else {
                            Toast.makeText(context, "Failed to add to cart", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            } else {
                Toast.makeText(context, "Stock not available at this moment", Toast.LENGTH_SHORT).show()
            }


        }
    }

    fun cartApiCall() {
        Common.getHelperActivity()?.getCartListCount()
    }


    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance(param1: String?, param2: String?): ProductInfoFragment {
            val fragment = ProductInfoFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }


}

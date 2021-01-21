package com.goodeggs.android.ui.address

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.goodeggs.android.utils.Constants
import com.goodeggs.android.MainViewModel
import com.goodeggs.android.R
import com.goodeggs.android.api.RecyclerViewClickListener
import com.goodeggs.android.bottommenu.HelperActivity
import com.goodeggs.android.model.CategoryData
import com.goodeggs.android.model.DealData
import com.goodeggs.android.utils.AppPreferences
import com.goodeggs.android.utils.Utility
import kotlinx.android.synthetic.main.activity_address_page.*
import org.json.JSONObject


class AddressFragment : Fragment(), RecyclerViewClickListener, TextWatcher {

    private lateinit var viewModel: MainViewModel
    var isAddressUpdate: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_address_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpdata()
        submit.setOnClickListener {
//            val fullnameStr = fullnameet.text.toString().trim()
//            val moblileStr = AppPreferences.mobile
            val doorstep = doornoet.text.toString().trim()
            val pincode = pincodeet.text.toString().trim()
            val landmark = landmarket.text.toString().trim()
            if (doorstep.isNullOrBlank()) {
                Utility.showSnackBarold(activity, middlelayout, "Please Enter Door No/Flat No ", 1)
            } else if (pincode.isNullOrBlank()) {
                Utility.showSnackBarold(activity, middlelayout, "Please Enter Pincode", 1)
            } else if (pincodeet.text.toString().length < 5) {
                Utility.showSnackBarold(activity, middlelayout, "Please Enter Valid Pincode", 1)
            } else if (landmark.isNullOrBlank()) {
                Utility.showSnackBarold(activity, middlelayout, "Please Enter landmark", 1)
            } else {
//                if (isAddressUpdate) {
                loading.visibility = View.VISIBLE
                val jsonAddress = JSONObject()
                jsonAddress.put("address", doorstep)
                jsonAddress.put("location", landmark)
                jsonAddress.put("city", AppPreferences.cityName)
                jsonAddress.put("pincode", AppPreferences.pincode)
                jsonAddress.put("state", "AP")
                jsonAddress.put("landmark", landmark)
                jsonAddress.put("user_id", AppPreferences.userId)

                viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
                viewModel.updateAddressReq(jsonAddress).observe(requireActivity(), Observer { userInfo ->
                    loading.visibility = View.GONE
                    if (userInfo?.message.equals("Success")) {

                        AppPreferences.isAddressAvailable = true
                        AppPreferences.doorno = doorstep
                        AppPreferences.pincode = pincode
                        AppPreferences.landmark = landmark

                        Toast.makeText(activity, "Address submitted successfully ", Toast.LENGTH_SHORT).show()
                        navigateTopaymentMode(userInfo.delivery_charges)

                    } else {
                        Toast.makeText(activity, "Failed to submit Address", Toast.LENGTH_SHORT).show()
                    }
                })
//                } else {
//                    navigateTopaymentMode()
//                }

            }
        }


    }

    fun navigateTopaymentMode(deliveryCharges : String) {
        val intent = Intent(requireContext(), HelperActivity::class.java)
        intent.putExtra(Constants.FRAGMENT_TITLE, "Payment Mode")
        intent.putExtra(Constants.FRAGMENT_KEY, 8009)
        intent.putExtra("CartData", requireArguments().getString("CartData"))
        intent.putExtra("DeliveryCharges", deliveryCharges)
        startActivity(intent)
    }

    fun setUpdata() {
        fullnameet.setText(AppPreferences.fullname + "")
        mobileet.setText(AppPreferences.mobile + "")

        if (AppPreferences.isAddressAvailable) {
            doornoet.setText(AppPreferences.doorno + "")
            pincodeet.setText(AppPreferences.pincode + "")
            landmarket.setText(AppPreferences.landmark + "")
            rangelabel.setText("Pincode(" + AppPreferences.cityName + "-" + AppPreferences.pincode + " In range of 5 km)")
            //Pincode(Tiruvuru-521235 In range of 5 km)
        } else {
            isAddressUpdate = true
        }

        doornoet.addTextChangedListener(this)
        pincodeet.addTextChangedListener(this)
        landmarket.addTextChangedListener(this)
    }


    fun loaddata() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

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
        fun newInstance(param1: String?, param2: String?): AddressFragment {
            val fragment = AddressFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

    override fun afterTextChanged(s: Editable?) {
        isAddressUpdate = true
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }


}

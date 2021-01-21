package com.goodeggs.android.ui.deals

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
import kotlinx.android.synthetic.main.offerpage.*


class OffersFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.offerpage, container, false)
    }


    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance(param1: String?, param2: String?): OffersFragment {
            val fragment = OffersFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

    override fun setMenuVisibility(isVisibleToUserL: Boolean) {
        super.setMenuVisibility(isVisibleToUserL)
        if (isVisibleToUserL) {
//            loaddata()
            getOfferImage()
        }

    }


    fun getOfferImage() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getOfferData()?.observe(viewLifecycleOwner, Observer { offerData ->
            if (offerData.get(0).success == 1) {
                if (offerData.get(0).pic != null) {
                    Glide.with(offerImg.getContext()).load(offerData.get(0).pic).into(offerImg)
                }
            } else {
                Toast.makeText(context, "Offer Image not available", Toast.LENGTH_SHORT).show()
            }

        })
    }


}

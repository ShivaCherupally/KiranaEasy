package com.goodeggs.android.ui.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.goodeggs.android.MainViewModel
import com.goodeggs.android.R
import com.goodeggs.android.utils.Utility
import kotlinx.android.synthetic.main.orderfailed.*


class OrderFailedFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.orderfailed, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        orderid.setText("Your Order ID is " + requireArguments().getString("OrderId"))

        continuebtn.setOnClickListener(View.OnClickListener {
            Utility.navigateToFeedOrBottomWithContext(requireContext())
        })

    }


    fun loaddata() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }


    /* override fun onRecyclerViewItemClick(view: View, movie: Movie) {
     }*/
    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance(param1: String?, param2: String?): OrderFailedFragment {
            val fragment = OrderFailedFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }


}

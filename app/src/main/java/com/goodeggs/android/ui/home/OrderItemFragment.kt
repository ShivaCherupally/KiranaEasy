package com.goodeggs.android.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.goodeggs.android.MainViewModel
import com.goodeggs.android.R
import kotlinx.android.synthetic.main.order_item_ge.*


class OrderItemFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    var list_of_items = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30")

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.order_item_ge, container, false)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val aa = ArrayAdapter(productonespn.context, android.R.layout.simple_spinner_item, list_of_items)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        productonespn.setAdapter(aa)

        productonespn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, positionLocal: Int, Id: Long) {
//                movies[position].min_qty = list_of_items[positionLocal].toInt()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        getProductsList()


    }


    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance(param1: String?, param2: String?): OrderItemFragment {
            val fragment = OrderItemFragment()
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
        }

    }

    /*fun loaddata() {
        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(aproductqty.context, android.R.layout.simple_spinner_item, list_of_items)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        aproductqty.setAdapter(aa)

        aproductqty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, positionLocal: Int, Id: Long) {
//                movies[position].min_qty = list_of_items[positionLocal].toInt()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }*/

    private fun getProductsList() {
        viewModel.getEggTypes().observe(viewLifecycleOwner, Observer { movies ->
            if (movies != null && movies.isNotEmpty() && movies.get(0).success == 1) {
                movies.get(0).product_name
                /*recyclerView.removeAllViews()
                recyclerView.isNestedScrollingEnabled = true
                recyclerView.setHasFixedSize(true)
                recyclerView.adapter = OrderAdapter(movies, this)*/
            } else {
//                recyclerView.adapter = EmptyDataAdapter(activity, "There are no orders yet", "", R.drawable.ic_shopping_cart, 6)
            }
        })
    }


}

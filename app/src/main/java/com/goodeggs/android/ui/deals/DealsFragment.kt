package com.goodeggs.android.ui.deals

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.goodeggs.android.MainViewModel
import com.goodeggs.android.R
import com.goodeggs.android.bottommenu.HelperActivity
import com.goodeggs.android.model.DealData
import com.goodeggs.android.ui.CommonSkeletonAdapter
import com.goodeggs.android.ui.EmptyDataAdapter
import com.goodeggs.android.ui.RController
import com.goodeggs.android.utils.Constants
import kotlinx.android.synthetic.main.common_recyclerview_frg.*


class DealsFragment : Fragment(), IDealViewClickListener {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.common_recyclerview_frg, container, false)
    }


    fun loaddata() {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = CommonSkeletonAdapter(RController.LOADING)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getDeals().observe(viewLifecycleOwner, Observer { movies ->
            recyclerView.removeAllViews()
            /*recyclerView.also {
                it!!.isNestedScrollingEnabled = true
                it.setHasFixedSize(true)
                it.adapter = DealAdapter(movies, this)
            }*/
            if (movies.size != 0 && !movies.isNullOrEmpty() && movies.get(0).banner_title != null) {
                recyclerView.adapter = DealAdapter(movies, this)
            } else {
                recyclerView.adapter = EmptyDataAdapter(activity, "", "No Deals available",
                        R.drawable.ic_list_interface_symbol, 6)
            }

        })
    }

    /*override fun onRecyclerViewItemClick(view: View, categoryData: CategoryData) {
        when (view.id) {
            R.id.button_book -> {
                Toast.makeText(requireContext(), "Book Button Clicked",Toast.LENGTH_LONG).show()
            }
            R.id.layout_like ->{
                Toast.makeText(requireContext(), "Like Layout Clicked",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onRecyclerViewItemDealsClick(view: View, dealData: DealData) {
    }*/

    /* override fun onRecyclerViewItemClick(view: View, movie: Movie) {
     }*/
    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance(param1: String?, param2: String?): DealsFragment {
            val fragment = DealsFragment()
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
            loaddata()
        }

    }

    override fun onItemDealsClick(position: Int, dealData: DealData) {
        val intent = Intent(activity, HelperActivity::class.java)
        intent.putExtra(Constants.FRAGMENT_TITLE, "Product Details")
        intent.putExtra(Constants.FRAGMENT_KEY, 8010)
        intent.putExtra("ProductId", dealData.product_id)
        requireContext().startActivity(intent)
    }
}

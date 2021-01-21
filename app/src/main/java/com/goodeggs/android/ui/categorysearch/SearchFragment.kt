package com.goodeggs.android.ui.categorysearch

import android.content.Intent
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.goodeggs.android.utils.Constants
import com.goodeggs.android.model.ProductInfo
import com.goodeggs.android.ui.EmptyDataAdapter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.goodeggs.android.MainViewModel
import com.goodeggs.android.R
import com.goodeggs.android.api.RecyclerViewClickListener
import com.goodeggs.android.bottommenu.HelperActivity
import com.goodeggs.android.model.CategoryData
import com.goodeggs.android.model.DealData
import com.goodeggs.android.ui.home.CategoryAdapter
import com.goodeggs.android.utils.GridItemDecoration
import kotlinx.android.synthetic.main.search_fragment.*


class SearchFragment : Fragment(), ISearchItemClickListener, RecyclerViewClickListener {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    fun intialDataLoad() {
        val linearLayoutManager = LinearLayoutManager(requireContext())

        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        categorylistRecycler.layoutManager = GridLayoutManager(requireContext(), 3)
        categorylistRecycler.addItemDecoration(GridItemDecoration(3, 3))

        val linearLayoutManager2 = LinearLayoutManager(requireContext())
        linearLayoutManager2.orientation = LinearLayoutManager.VERTICAL
        recyclerViewsearch.layoutManager = linearLayoutManager2

        eSearchBarNew.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                if (editable.toString().length > 2) {
                    categorylistRecycler.visibility = View.GONE
                    iClearText.visibility = View.VISIBLE
                    Log.e("keyString", editable.toString())
                    loaddata(editable.toString())
                } else {
//                    if (editable.toString().length == 0) {
                        categorylistRecycler.visibility = View.VISIBLE
                        iClearText.visibility = View.GONE
                        recyclerViewsearch.visibility = View.GONE
//                    }

                }
            }
        })
        iClearText.setOnClickListener {
            eSearchBarNew.setText("")
            recyclerViewsearch.visibility = View.GONE
            categorylistRecycler.visibility = View.VISIBLE
        }
        loading.visibility = View.VISIBLE
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        /*viewModel.getHolidays().observe(viewLifecycleOwner, Observer { movies ->
            loading.visibility = View.GONE
            categorylistRecycler.visibility = View.VISIBLE
            categorylistRecycler.adapter = CategoryAdapter(movies, this)
        })*/
    }


    fun loaddata(entry: String) {
        loading.visibility = View.VISIBLE
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.searchCategoryViewmodel(entry)?.observe(this, Observer { searchdata ->
            loading.visibility = View.GONE
            recyclerViewsearch.removeAllViews()
            recyclerViewsearch.visibility = View.VISIBLE
            if (searchdata!!.isNotEmpty()) {
                recyclerViewsearch.adapter = SearchCategoryAdapter(searchdata, this)
            } else {
                recyclerViewsearch.adapter = EmptyDataAdapter(activity, Constants.UH_OH, Constants.NO_DATA_AVAILABLE, R.drawable.ic_network, 0)
            }
        })
    }

    override fun onRecyclerViewItemClick(view: Button, productInfo: ProductInfo) {
        val intent = Intent(activity, HelperActivity::class.java)
        intent.putExtra(Constants.FRAGMENT_TITLE, "Product Details")
        intent.putExtra(Constants.FRAGMENT_KEY, 8010)
        intent.putExtra("ProductId", productInfo.product_id)
        startActivity(intent)
    }


    /* override fun onRecyclerViewItemClick(view: View, movie: Movie) {
     }*/
    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance(param1: String?, param2: String?): SearchFragment {
            val fragment = SearchFragment()
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
            intialDataLoad()
        }

    }

    override fun onRecyclerViewItemClick(view: View, categoryData: CategoryData) {
        if (categoryData.category_id != null && !categoryData.category_id.equals("0")) {
            val intent = Intent(activity, HelperActivity::class.java)
            intent.putExtra(Constants.FRAGMENT_TITLE, categoryData.category_name)
            intent.putExtra(Constants.FRAGMENT_KEY, 8013)
            intent.putExtra("CategoryId", categoryData.category_id)
            startActivity(intent)
        } else {
            Toast.makeText(requireContext(), "Category data not available", Toast.LENGTH_LONG).show()
        }
    }

    override fun onRecyclerViewItemDealsClick(view: View, dealData: DealData) {
    }
}

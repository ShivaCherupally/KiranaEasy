package com.goodeggs.android.ui.subcategory

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.goodeggs.android.utils.Constants
import com.goodeggs.android.MainViewModel
import com.goodeggs.android.R
import com.goodeggs.android.bottommenu.HelperActivity
import com.goodeggs.android.model.SubCategoryInfo
import com.goodeggs.android.ui.CommonSkeletonAdapter
import com.goodeggs.android.ui.EmptyDataAdapter
import com.goodeggs.android.ui.RController
import kotlinx.android.synthetic.main.common_recycler.*


class SubCategoryListFragment : Fragment(), ISubCategoryItemClick {

    private lateinit var viewModel: MainViewModel

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
        recyclerViewCommon.setHasFixedSize(true)
        recyclerViewCommon.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL))
        recyclerViewCommon.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        recyclerViewCommon.layoutManager = GridLayoutManager(requireContext(), 3);
        recyclerViewCommon.removeItemDecorationsLine()
        recyclerViewCommon.adapter = CommonSkeletonAdapter(RController.LOADING)

        var categoryId: String? = requireArguments().getString("CategoryId")
        if (categoryId != null) {
            Log.d("CategoryId", categoryId)
        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getSubCatogoryList(categoryId).observe(viewLifecycleOwner, Observer { movies ->
            recyclerViewCommon.removeAllViews()
            if (movies.size != 0 && !movies.isNullOrEmpty()) {
                recyclerViewCommon.adapter = SubCategoryItemAdapter(movies, this)
            } else {
                recyclerViewCommon.adapter = EmptyDataAdapter(activity, Constants.UH_OH, Constants.NO_DATA_AVAILABLE, R.drawable.ic_network, 6)
            }
        })

    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance(param1: String?, param2: String?): SubCategoryListFragment {
            val fragment = SubCategoryListFragment()
            val args = Bundle()

            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onItemView(position: Int, subCategoryInfo: SubCategoryInfo) {
        val intent = Intent(activity, HelperActivity::class.java)
        intent.putExtra(Constants.FRAGMENT_TITLE, subCategoryInfo.subcategory_title)
        intent.putExtra(Constants.FRAGMENT_KEY, 8003)
        intent.putExtra("CategoryId", requireArguments().getString("CategoryId"))
        intent.putExtra("subcategory_id", subCategoryInfo.subcategory_id)
        startActivity(intent)
    }

    fun RecyclerView.removeItemDecorationsLine() {
        while (this.itemDecorationCount > 0) {
            this.removeItemDecorationAt(0)
        }
    }


}

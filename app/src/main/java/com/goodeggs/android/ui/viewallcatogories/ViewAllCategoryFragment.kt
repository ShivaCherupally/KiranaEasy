//package com.goodeggs.android.ui.viewallcatogories
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.goodeggs.android.MainViewModel
//import com.goodeggs.android.R
//import com.goodeggs.android.api.RecyclerViewClickListener
//import com.goodeggs.android.model.CategoryData
//import com.goodeggs.android.model.DealData
//import com.goodeggs.android.ui.deals.DealAdapter
//import kotlinx.android.synthetic.main.common_recyclerview_frg.*
//
//
//class ViewAllCategoryFragment : Fragment(), RecyclerViewClickListener {
//
//    private lateinit var viewModel: MainViewModel
//
//    override fun onCreateView(
//            inflater: LayoutInflater, container: ViewGroup?,
//            savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.common_recyclerview_frg, container, false)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        loaddata()
//    }
//
//
//    fun loaddata() {
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//        viewModel.getDeals().observe(viewLifecycleOwner, Observer { movies ->
//            recyclerView.also {
//                val linearLayoutManager = LinearLayoutManager(requireContext())
//                linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
//                it!!.layoutManager = linearLayoutManager
//                it.isNestedScrollingEnabled = true
//                it.setHasFixedSize(true)
//                it.adapter = DealAdapter(movies, this)
//            }
//
//        })
//    }
//
//    override fun onRecyclerViewItemClick(view: View, categoryData: CategoryData) {
//        when (view.id) {
//            /*R.id.button_book -> {
//                Toast.makeText(requireContext(), "Book Button Clicked",Toast.LENGTH_LONG).show()
//            }
//            R.id.layout_like ->{
//                Toast.makeText(requireContext(), "Like Layout Clicked",Toast.LENGTH_LONG).show()
//            }*/
//        }
//    }
//
//    override fun onRecyclerViewItemDealsClick(view: View, dealData: DealData) {
//        TODO("Not yet implemented")
//    }
//
//    /* override fun onRecyclerViewItemClick(view: View, movie: Movie) {
//     }*/
//    companion object {
//        private const val ARG_PARAM1 = "param1"
//        private const val ARG_PARAM2 = "param2"
//        fun newInstance(param1: String?, param2: String?): ViewAllCategoryFragment {
//            val fragment = ViewAllCategoryFragment()
//            val args = Bundle()
//            args.putString(ARG_PARAM1, param1)
//            args.putString(ARG_PARAM2, param2)
//            fragment.arguments = args
//            return fragment
//        }
//    }
//
//    override fun setMenuVisibility(isVisibleToUserL: Boolean) {
//        super.setMenuVisibility(isVisibleToUserL)
//        if (isVisibleToUserL) {
//            loaddata()
//        }
//
//    }
//}

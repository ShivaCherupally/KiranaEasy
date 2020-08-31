package com.kiranam.android

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.drawee.backends.pipeline.Fresco
import com.kiranam.android.databinding.ActivityMainBinding
import com.kiranam.android.models.Message
import com.kiranam.android.models.NetworkCall
import com.kiranam.android.models.NetworkStatus
import com.kiranam.android.models.Student
import com.kiranam.android.utils.Utility

class StudentListActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var viewModel: StudentViewModel? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var adapter: StudentsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        Fresco.initialize(this)
        viewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUp()
    }

    private fun setUp() {
        binding!!.recyclerView.layoutManager = LinearLayoutManager(this).also { linearLayoutManager = it }
        binding!!.swipeRefreshLayout.setOnRefreshListener {
            binding!!.swipeRefreshLayout.isRefreshing = false
            viewModel!!.fetchAllStudents()
        }


        /*binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();


                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                    List<Student> appendList;
                    if ((appendList = viewModel.getLoadMore()) != null) {
                        if (adapter != null) {
                            adapter.addStudents(appendList);
                        }
                    }
                }

            }
        });*/
        viewModel!!.networkCall.observe(this, Observer { networkCall: NetworkCall ->
            when (networkCall.networkTag) {
                StudentViewModel.NetworkTags.GET_ALL_STUDENTS -> updateUiForGetAllStudents(networkCall)
            }
        })
        viewModel!!.message.observe(this, Observer { message: Message -> Utility.showSnackBar(this, binding!!.root, message.message, message.type) })
        viewModel!!.allStudents.observe(this, Observer { list: List<Student?>? -> binding!!.recyclerView.adapter = StudentsAdapter(list).also { adapter = it } })
        viewModel!!.fetchAllStudents()
    }

    private fun updateUiForGetAllStudents(networkCall: NetworkCall) {
        when (networkCall.networkStatus) {
            NetworkStatus.NO_INTERNET -> {
                binding!!.progressBar.visibility = View.GONE
                binding!!.recyclerView.adapter = EmptyDataAdapter(this, Constants.PLEASE_CHECK_INTERNET, R.drawable.no_internet, 0)
            }
            NetworkStatus.IN_PROCESS -> {
                binding!!.recyclerView.adapter = null
                binding!!.progressBar.visibility = View.VISIBLE
            }
            NetworkStatus.NO_DATA -> {
                binding!!.progressBar.visibility = View.VISIBLE
                binding!!.recyclerView.adapter = EmptyDataAdapter(this, Constants.NO_DATA_AVAILABLE, R.drawable.ic_empty_black, 1)
            }
            NetworkStatus.SUCCESS -> binding!!.progressBar.visibility = View.GONE
            NetworkStatus.FAIL, NetworkStatus.ERROR -> {
                binding!!.progressBar.visibility = View.GONE
                binding!!.recyclerView.adapter = EmptyDataAdapter(this, Constants.SOMETHING_WENT_WRONG, R.drawable.no_internet, 1)
            }
        }
    }

    companion object {
        var instance: StudentListActivity? = null
            private set
    }
}
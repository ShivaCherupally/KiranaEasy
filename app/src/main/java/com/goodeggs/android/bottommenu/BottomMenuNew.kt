package com.goodeggs.android.bottommenu

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.goodeggs.android.MainViewModel
import com.goodeggs.android.R
import com.goodeggs.android.utils.AppPreferences
import com.goodeggs.android.utils.Common
import com.goodeggs.android.utils.Constants
import com.goodeggs.android.viewpager.HomeViewPagerAdapterNew
import kotlinx.android.synthetic.main.bottom_sub_layout.*

class BottomMenuNew : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_sub_layout)

//        getCartCount()

        swipeDisableViewPager.setPagingEnabled(false)
        swipeDisableViewPager.offscreenPageLimit = 7
        swipeDisableViewPager.adapter = HomeViewPagerAdapterNew(supportFragmentManager, applicationContext)

        rlHome.setOnClickListener {
//            getCartCount()
            swipeDisableViewPager.setCurrentItem(0, false)
            imageViewHome.setImageDrawable(getDrawable(R.drawable.ic_baseline_home_24_fill))
            imageViewSearch.setImageDrawable(getDrawable(R.drawable.ic_baseline_search_24))
            imageViewChat.setImageDrawable(getDrawable(R.drawable.ic_deals))
            imageViewMenu.setImageDrawable(getDrawable(R.drawable.ic_baseline_menu_24))
        }
        /*rlSearch.setOnClickListener {
//            getCartCount()
            swipeDisableViewPager.setCurrentItem(1, false)
            imageViewHome.setImageDrawable(getDrawable(R.drawable.ic_baseline_home_24))
            imageViewSearch.setImageDrawable(getDrawable(R.drawable.ic_baseline_search_24_fill))
            imageViewChat.setImageDrawable(getDrawable(R.drawable.ic_deals))
            imageViewMenu.setImageDrawable(getDrawable(R.drawable.ic_baseline_menu_24))
        }*/
        rlDealToday.setOnClickListener {
            swipeDisableViewPager.setCurrentItem(2, false)
            imageViewHome.setImageDrawable(getDrawable(R.drawable.ic_baseline_home_24))
            imageViewSearch.setImageDrawable(getDrawable(R.drawable.ic_baseline_search_24))
            imageViewChat.setImageDrawable(getDrawable(R.drawable.ic_deals_fill))
            imageViewMenu.setImageDrawable(getDrawable(R.drawable.ic_baseline_menu_24))
        }
        rlMenu!!.setOnClickListener {
            swipeDisableViewPager!!.setCurrentItem(4, false)
            imageViewHome.setImageDrawable(getDrawable(R.drawable.ic_baseline_home_24))
            imageViewSearch.setImageDrawable(getDrawable(R.drawable.ic_baseline_search_24))
            imageViewChat.setImageDrawable(getDrawable(R.drawable.ic_deals))
            imageViewMenu.setImageDrawable(getDrawable(R.drawable.ic_baseline_menu_24_fill))
        }

        rlNoticationLayout.setOnClickListener {
            val intent = Intent(this, HelperActivity::class.java)
            intent.putExtra(Constants.FRAGMENT_TITLE, "Cart List")
            intent.putExtra(Constants.FRAGMENT_KEY, 8005)
            startActivity(intent)
        }
    }

    override fun onResume() {
        Common.setBottomMenuNew(this)
        super.onResume()
    }

    fun getCartCount() {
        /*val viewModel: MainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getCartList(AppPreferences.userId).observe(this, Observer { cartItems ->
            if (cartItems.isNotEmpty() && cartItems.get(0).success == 1) {
                badgeCounttv.visibility = View.VISIBLE
                badgeCounttv.text = cartItems.size.toString()
                AppPreferences.cartCount = cartItems.size.toString()
            } else {
                AppPreferences.cartCount = "0"
                badgeCounttv.visibility = View.GONE
            }
        })*/
    }

    fun updatedCartCount(cartCount: Int) {
        if (cartCount != 0) {
            badgeCounttv.visibility = View.VISIBLE
            badgeCounttv.text = cartCount.toString()
        } else {
            badgeCounttv.visibility = View.GONE
        }
    }


}
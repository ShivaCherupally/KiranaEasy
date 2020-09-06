package com.kiranam.android.bottommenu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kiranam.android.R
import com.kiranam.android.databinding.ActivityMenuBind
import com.kiranam.android.viewpager.HomeViewPagerAdapter


class BottomMenuActivity : AppCompatActivity() {
    private var binding: ActivityMenuBind? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.bottom_sub_layout)

        binding!!.swipeDisableViewPager.setPagingEnabled(false)
        binding!!.swipeDisableViewPager.offscreenPageLimit = 7
        binding!!.swipeDisableViewPager.adapter = HomeViewPagerAdapter(supportFragmentManager, this)


        binding!!.imageViewHome.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_home_24_fill))

        binding!!.rlHome.setOnClickListener { view ->
            binding!!.swipeDisableViewPager.setCurrentItem(0, false)
            binding!!.imageViewHome.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_home_24_fill))
            binding!!.imageViewSearch.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_search_24))
            binding!!.imageViewChat.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_transfer_within_a_station_24))
            binding!!.imageViewMenu.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_settings_24))
        }


        binding!!.rlSearch.setOnClickListener { view ->
            binding!!.swipeDisableViewPager.setCurrentItem(1, false)
            binding!!.imageViewHome.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_home_24))
            binding!!.imageViewSearch.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_search_24_fill))
            binding!!.imageViewChat.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_transfer_within_a_station_24))
            binding!!.imageViewMenu.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_settings_24))
        }


        binding!!.rlChat.setOnClickListener { view ->
            binding!!.swipeDisableViewPager.setCurrentItem(3, false)
            binding!!.imageViewHome.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_home_24))
            binding!!.imageViewSearch.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_search_24))
            binding!!.imageViewChat.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_transfer_within_a_station_24_fill))
            binding!!.imageViewMenu.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_settings_24))
        }


        binding!!.rlMenu.setOnClickListener { view ->
            binding!!.swipeDisableViewPager.setCurrentItem(4, false)
            binding!!.imageViewHome.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_home_24))
            binding!!.imageViewSearch.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_search_24))
            binding!!.imageViewChat.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_transfer_within_a_station_24))
            binding!!.imageViewMenu.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_settings_24_fill))
        }

    }
}
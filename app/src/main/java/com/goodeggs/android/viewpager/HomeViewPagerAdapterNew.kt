package com.goodeggs.android.viewpager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.goodeggs.android.dummy.DummyFragment
import com.goodeggs.android.ui.categorysearch.SearchFragment
import com.goodeggs.android.ui.deals.DealsFragment
import com.goodeggs.android.ui.deals.OffersFragment
import com.goodeggs.android.ui.home.HomeFragment
import com.goodeggs.android.ui.home.OrderItemFragment
import com.goodeggs.android.ui.menu.HomeMenuFragment

/**
 * Created by User on 28-07-2018.
 */
class HomeViewPagerAdapterNew(fm: FragmentManager?, var context: Context) : FragmentPagerAdapter(fm!!, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    var type = 1
    var CelebId: String? = null
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        return if (type == 1) {
            when (position) {
                0 -> fragment = HomeFragment()
//                1 -> fragment = SearchFragment.newInstance(null, null)
                1 -> fragment = DummyFragment.newInstance(null, null)
                2 -> fragment = OffersFragment.newInstance(null, null)
                3 -> fragment = HomeMenuFragment.newInstance(null, null)
            }
            fragment!!
        } else {
            fragment!!
        }
    }

    override fun getCount(): Int {
        return if (type == 1) if (CelebId == null) 4 else 1 else 0
    }

}
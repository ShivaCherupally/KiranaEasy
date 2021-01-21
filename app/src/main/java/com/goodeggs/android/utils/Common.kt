package com.goodeggs.android.utils

import com.goodeggs.android.bottommenu.BottomMenuNew
import com.goodeggs.android.bottommenu.HelperActivity
import com.goodeggs.android.ui.cartlist.CartListFragment
import com.goodeggs.android.ui.home.HomeFragment

object Common {

    private var auditionTalantFragmentt: CartListFragment? = null
    private var bottomMenuNew: BottomMenuNew? = null
    private var homeFragment: HomeFragment? = null

    private var helperActivity: HelperActivity? = null

    private var common: Common? = null


    /*fun getInstance(): Common? {
        if (common == null) {
            common = Common()
        }
        return common
    }*/


    fun getAuditionTalantFragmentttt(): CartListFragment? {
        return auditionTalantFragmentt
    }

    fun setAuditionTalantFragment(auditionTalantFragment: CartListFragment) {
        auditionTalantFragmentt = auditionTalantFragment
    }

    fun getHomeFrag(): HomeFragment? {
        return homeFragment
    }

    fun setHomeFrag(homeFragmentt: HomeFragment) {
        homeFragment = homeFragmentt
    }

    fun getBottomMenuNew(): BottomMenuNew? {
        return bottomMenuNew
    }

    fun setBottomMenuNew(bottomMenuNewLocal: BottomMenuNew) {
        bottomMenuNew = bottomMenuNewLocal
    }

    fun getHelperActivity(): HelperActivity? {
        return helperActivity
    }

    fun setHelperActivity(helperActivityLocal: HelperActivity) {
        helperActivity = helperActivityLocal
    }


}
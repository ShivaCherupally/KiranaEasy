package com.goodeggs.android.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.goodeggs.android.R
import com.goodeggs.android.utils.AppPreferences
import kotlinx.android.synthetic.main.profile_page.*

class ProfilePageFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fullnameet.setText(AppPreferences.fullname + "")
        mobileet.setText(AppPreferences.mobile + "")
        emailidet.setText(AppPreferences.email + "")
//        cityEt.setText(AppPreferences.cityName+"-"+AppPreferences.pincode)

    }


    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance(param1: String?, param2: String?): ProfilePageFragment {
            val fragment = ProfilePageFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }


}
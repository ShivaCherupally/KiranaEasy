package com.goodeggs.android.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.goodeggs.android.MainViewModel
import com.goodeggs.android.R
import com.goodeggs.android.ui.login.LoginActivity
import com.goodeggs.android.utils.Utility
import kotlinx.android.synthetic.main.change_paasword_frg.*

class ChangePasswordFragment : Fragment() {
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.change_paasword_frg, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        submitbtn.setOnClickListener {
            val newpassword = newpasswordet.text.toString().trim()
            val confirmpassword = confirmpasswordet.text.toString().trim()

            if (newpassword.isNullOrBlank()) {
                Utility.showSnackBarold(requireActivity(), middlelayout, "Please Enter Password", 1)
            } else if (confirmpassword.isNullOrBlank()) {
                Utility.showSnackBarold(requireActivity(), middlelayout, "Please Enter Confirm Password", 1)
            } else if (!newpassword.equals(confirmpassword)) {
                Utility.showSnackBarold(requireActivity(), middlelayout, "Passwords do not matched", 1)
            } else {
                loading.visibility = View.VISIBLE
                viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
                viewModel.changepasswordReq(newpassword)?.observe(requireActivity(), Observer { userInfo ->
                    if (userInfo?.message.equals("Success")) {
                        loading.visibility = View.GONE
                        Toast.makeText(context, "Password changed successfully", Toast.LENGTH_SHORT).show()
                        Utility.clearLoginData()
                        val intent = Intent(activity, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        loading.visibility = View.GONE
                        Toast.makeText(activity, "password failed to changed", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }

    }


    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance(param1: String?, param2: String?): ChangePasswordFragment {
            val fragment = ChangePasswordFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }


}
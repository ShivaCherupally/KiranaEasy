package com.goodeggs.android.bottommenu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.goodeggs.android.MainViewModel
import com.goodeggs.android.R
import com.goodeggs.android.ui.address.AddressFragment
import com.goodeggs.android.ui.address.OrderFailedFragment
import com.goodeggs.android.ui.address.OrderSuccessFragment
import com.goodeggs.android.ui.cartlist.CartListFragment
import com.goodeggs.android.ui.home.HomeFragment
import com.goodeggs.android.ui.myorders.MyOrderListFragment
import com.goodeggs.android.ui.myorders.OrderInfoFragment
import com.goodeggs.android.ui.paymentmode.PaymentModeFragment
import com.goodeggs.android.ui.product.ProductInfoFragment
import com.goodeggs.android.ui.profile.ChangePasswordFragment
import com.goodeggs.android.ui.profile.ProfilePageFragment
import com.goodeggs.android.ui.subcategory.SubCategoryListFragment
import com.goodeggs.android.ui.subcatogory.ProductListFragment
import com.goodeggs.android.ui.wesitesview.WebUrlSitesFragment
import com.goodeggs.android.utils.AppPreferences
import com.goodeggs.android.utils.Common
import com.goodeggs.android.utils.Constants
import com.goodeggs.android.utils.Utility
import kotlinx.android.synthetic.main.activity_helper.*

class HelperActivity : AppCompatActivity() {
    private var fragment_container: FrameLayout? = null
    private lateinit var viewModelLocal: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_helper)
        instance = this
        setUp()

        Common.setHelperActivity(this)

        viewModelLocal = ViewModelProvider(this).get(MainViewModel::class.java)

        setUpFragment()

        backimg.setOnClickListener {
            backStateRequestedRefresh()
        }

        rlCartLL.setOnClickListener {
            val intent = Intent(this, HelperActivity::class.java)
            intent.putExtra(Constants.FRAGMENT_TITLE, "Cart List")
            intent.putExtra(Constants.FRAGMENT_KEY, 8005)
            startActivity(intent)
        }
    }


    fun setUp() {
        fragment_container = findViewById<FrameLayout>(R.id.fragment_container)
    }

    private fun setUpFragment() {
        val intent = intent
        val fragmentKey = intent.getIntExtra(Constants.FRAGMENT_KEY, 0)
        val title = intent.getStringExtra(Constants.FRAGMENT_TITLE)
        toolbar.visibility = View.VISIBLE
        rlCartLL.visibility = View.GONE
        //
        Log.d(Constants.FRAGMENT_KEY, "" + fragmentKey)
        if (fragmentKey > 0) {
            var fragment: Fragment? = null
            when (fragmentKey) {
                8003 -> {
                    getCartListCount()
                    tvTitle.setText(title)
                    val arguments = Bundle()
                    arguments.putString("CategoryId", intent.getStringExtra("CategoryId"))
                    arguments.putString("subcategory_id", intent.getStringExtra("subcategory_id"))
                    fragment = ProductListFragment.newInstance(intent.getStringExtra("CategoryId"), null)
                    fragment.setArguments(arguments)
                }
                8005 -> {
                    tvTitle.setText(title)
                    fragment = CartListFragment.newInstance(null, null)
                }
                8006 -> {
                    tvTitle.setText(title)
                    val arguments = Bundle()
                    arguments.putString("CartData", intent.getStringExtra("CartData"))
                    fragment = AddressFragment.newInstance(null, null)
                    fragment.setArguments(arguments)
                }
                8007 -> {
                    tvTitle.setText(title)
                    var arguments = Bundle()
                    toolbar.visibility = View.GONE
                    arguments.putString("OrderId", intent.getStringExtra("OrderId"))
                    fragment = OrderSuccessFragment.newInstance(null, null)
                    fragment.setArguments(arguments)
                }
                8008 -> {
                    tvTitle.setText(title)
                    fragment = HomeFragment()
                }
                8009 -> {
                    tvTitle.setText(title)
                    var arguments = Bundle()
                    arguments.putString("CartData", intent.getStringExtra("CartData"))
                    arguments.putString("DeliveryCharges", intent.getStringExtra("DeliveryCharges"))
                    fragment = PaymentModeFragment.newInstance(null, null)
                    fragment.setArguments(arguments)
                }
                8010 -> {
                    getCartListCount()
                    tvTitle.setText(title)
                    var arguments = Bundle()
                    arguments.putString("ProductId", intent.getStringExtra("ProductId"))
                    fragment = ProductInfoFragment.newInstance(null, null)
                    fragment.setArguments(arguments)
                }
                8011 -> {
                    tvTitle.setText(title)
                    fragment = ProfilePageFragment()
                }
                8012 -> {
                    tvTitle.setText(title)
                    var arguments = Bundle()
                    fragment = OrderFailedFragment.newInstance(null, null)
                    fragment.setArguments(arguments)
                }
                8013 -> {
                    getCartListCount()
                    tvTitle.setText(title)
                    var arguments = Bundle()
                    arguments.putString("CategoryId", intent.getStringExtra("CategoryId"))
                    fragment = SubCategoryListFragment.newInstance(intent.getStringExtra("CategoryId"), null)
                    fragment.setArguments(arguments)
                }
                8014 -> {
                    tvTitle.setText(title)
                    var arguments = Bundle()
                    arguments.putString("OrderId", intent.getStringExtra("OrderId"))
                    fragment = OrderInfoFragment()
                    fragment.setArguments(arguments)
                }
                9000 -> {
                    tvTitle.setText(title)
                    fragment = ProfilePageFragment()
                }

                9001 -> {
                    tvTitle.setText(title)
                    fragment = MyOrderListFragment()
                }

                9002 -> {
                    tvTitle.setText(title)
                    fragment = HomeFragment()
                }

                9003 -> {
                    tvTitle.setText(title)
                    fragment = ChangePasswordFragment()
                }
                9004 -> {
                    tvTitle.setText(title)
                    var arguments = Bundle()
                    arguments.putString("FAQ", "About us")
                    tvTitle.setText(title)
                    fragment = WebUrlSitesFragment()
                    fragment.setArguments(arguments)
                }
                9005 -> {
                    tvTitle.setText(title)
                    var arguments = Bundle()
                    arguments.putString("FAQ", "About us")
                    tvTitle.setText(title)
                    fragment = WebUrlSitesFragment()
                    fragment.setArguments(arguments)
                }
                9006 -> {
                    var arguments = Bundle()
                    arguments.putString("FAQ", "Contact Us")
                    tvTitle.setText(title)
                    fragment = WebUrlSitesFragment()
                    fragment.setArguments(arguments)
                }

            }
            if (fragment != null) {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commitAllowingStateLoss()
            }
        }
    }

    companion object {
        var instance: HelperActivity? = null
    }

    override fun onBackPressed() {
        super.onBackPressed()
        backStateRequestedRefresh()
    }

    fun backStateRequestedRefresh() {

        val intent = intent
        val fragmentKey = intent.getIntExtra(Constants.FRAGMENT_KEY, 0)
        Log.d(Constants.FRAGMENT_KEY, "" + fragmentKey)

        when (intent.getIntExtra(Constants.FRAGMENT_KEY, 0)) {

            8007 -> {
                Utility.navigateToFeedOrBottomWithContext(applicationContext)
                return
            }
            8005 -> {  //1

                finish()
                val fragmentList = supportFragmentManager.fragments
                for (fragment in fragmentList) {
                    if (fragment is HomeFragment) {
                        Common.getBottomMenuNew()?.updatedCartCount(AppPreferences.cartCount.toInt())
//                        HomeFragment
//                        Common.getHomeFrag()?.loaddata()
                    } else {

                        if (AppPreferences.cartCount.toInt() != 0) {
                            updatedCartListCount(AppPreferences.cartCount.toInt())
                        } else {
                            updatedCartListCount(0)
                        }

//                        Common.getHomeFrag()?.loaddata()
                    }
                }
                return
            }
            8010 -> {
                finish()
                updatedCartListCount(AppPreferences.cartCount.toInt())
                return
            }
            8003 -> {
                finish()
//                getCartListCount()
                return
            }
            8013 -> {  //2
                finish()
//                getCartListCount()
                Common.getBottomMenuNew()?.getCartCount()
                return
            }
            else -> {
                finish()
                return
            }
        }
    }

    fun getCartListCount() {
        viewModelLocal.getCartList(AppPreferences.userId).observe(this, Observer { cartItems ->
            if (cartItems.isNotEmpty() && cartItems.get(0).success == 1) {
                AppPreferences.cartCount = cartItems.size.toString()
                rlCartLL.visibility = View.VISIBLE
                cartCountTv.visibility = View.VISIBLE
                cartCountTv.text = cartItems.size.toString()
            } else {
                AppPreferences.cartCount = "0"
                rlCartLL.visibility = View.GONE
                cartCountTv.visibility = View.GONE
            }
        })
    }

    fun updatedCartListCount(cartCount: Int) {
        if (cartCount != 0) {
            rlCartLL.visibility = View.VISIBLE
            cartCountTv.visibility = View.VISIBLE
            cartCountTv.text = cartCount.toString()
        } else {
            rlCartLL.visibility = View.GONE
            cartCountTv.visibility = View.GONE
        }
    }

}
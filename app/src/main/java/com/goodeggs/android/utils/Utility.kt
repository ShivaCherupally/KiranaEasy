package com.goodeggs.android.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.util.Patterns
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.goodeggs.android.ApplicationController
import com.goodeggs.android.R
import com.goodeggs.android.bottommenu.BottomMenuNew
import com.goodeggs.android.bottommenu.HelperActivity
import com.goodeggs.android.ui.cartlist.CartListFragment

object Utility {

    private var auditionTalantFragmentt: CartListFragment? = null


    @JvmStatic
    val context: Context
        get() = ApplicationController.instance.applicationContext

    fun showSnackBarold(activity: Context?, view: View?, text: String?, type: Int) {
        if (view == null || text == null || activity == null) {
            return
        }
        val snackBar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
        val txtMessage = snackBar.view.findViewById<View>(R.id.snackbar_text) as TextView
        txtMessage.setTextColor(ContextCompat.getColor(activity, R.color.white))
        if (type == 2) snackBar.view.setBackgroundColor(ContextCompat.getColor(activity, R.color.black)) else if (type == 1) snackBar.view.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorAccent)) else {
            snackBar.view.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorAccent))
        }
        snackBar.show()
    }

    @JvmStatic
    fun dpSize(context: Context, sizeInDp: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (sizeInDp * scale + 0.5f).toInt()
    }

    fun getTypeface(value: Int, context: Context): Typeface? {
        var path = "fonts/sf_compact_display/sf_medium.otf"
        when (value) {
            8 -> path = "fonts/sf_compact_display/sf_light.otf"
            9 -> path = "fonts/sf_compact_display/sf_regular.otf"
            10 -> {
                path = "fonts/sf_compact_display/sf_medium.otf"
            }
            11 -> path = "fonts/sf_compact_display/sf_semibold.otf"
            12 -> path = "fonts/sf_compact_display/sf_bold.otf"
            13 -> path = "fonts/sf_compact_display/sf_black.otf"
        }
        return Typeface.createFromAsset(context.assets, path)
    }

    fun showSnackBar(activity: Context?, linearLayout: RelativeLayout?, text: String?, type: Int) {
        if (linearLayout == null || text == null || activity == null) {
            return
        }
        val snackBar = Snackbar.make(linearLayout, text, Snackbar.LENGTH_SHORT)
        val txtMessage = snackBar.view.findViewById<View>(R.id.snackbar_text) as TextView
        txtMessage.setTextColor(ContextCompat.getColor(activity, R.color.white))
        if (type == 2) snackBar.view.setBackgroundColor(ContextCompat.getColor(activity, R.color.black)) else if (type == 1) snackBar.view.setBackgroundColor(ContextCompat.getColor(activity, R.color.red)) else {
            snackBar.view.setBackgroundColor(ContextCompat.getColor(activity, R.color.red))
        }
        snackBar.show()
    }


    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValid(@NonNull text: CharSequence, isEmpty: Boolean): Boolean {
        // Ideally phone numbers should be validated via Google phonelib library.
        // This is just for demo purpose.
        val regex = "[^\\d]"
        val PhoneDigits = text.toString().replace(regex.toRegex(), "")
        return !isEmpty && PhoneDigits.length >= 10
    }

    fun getAuditionTalantFragment(): CartListFragment? {
        return auditionTalantFragmentt
    }

    fun setAuditionTalantFragment(auditionTalantFragment: CartListFragment) {
        auditionTalantFragmentt = auditionTalantFragment
    }

    fun shareSocialNetwork(mContext: Context, pageAccess: String?) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.type = "text/plain"
        if (pageAccess != null && !pageAccess.isEmpty()) {
            if (pageAccess == "INVITE_FRIEND") {
                intent.putExtra(Intent.EXTRA_TEXT,
                        "Hi, I'm inviting you to join Kirana Easy, please visit : https://play.google.com/store/apps/details?id=com.goodeggs.android&hl=en_IN")
            }
        }
        mContext.startActivity(Intent.createChooser(intent, "Share"))
    }

    fun navigateScreens(mContext: Context, title: String, keyno: Int) {
        val intent = Intent(mContext, HelperActivity::class.java)
        intent.putExtra(Constants.FRAGMENT_TITLE, title)
        intent.putExtra(Constants.FRAGMENT_KEY, keyno)
        mContext.startActivity(intent)
    }

    fun navigateToFeedOrBottomWithContext(context: Context) {
        val i = Intent(context, BottomMenuNew::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(i)
    }


    fun clearLoginData() {
        AppPreferences.isLogin = false
        AppPreferences.fullname = ""
        AppPreferences.mobile = ""
        AppPreferences.password = ""
        AppPreferences.email = ""
        AppPreferences.isAddressAvailable = false
        AppPreferences.doorno = ""
        AppPreferences.pincode = ""
        AppPreferences.landmark = ""
    }

    fun getAppVersionCode(context: Context): String? {
        var pInfo: PackageInfo? = null
        var version: String? = null
        var version_code = 0
        try {
            pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            version = pInfo.versionName
            version_code = pInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return version_code.toString() + ""
    }

    fun checkInternetConnection(mContext: Context): Boolean {
        val connMgr = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        return connMgr.activeNetworkInfo != null
    }

}
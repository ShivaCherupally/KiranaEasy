package com.kiranam.android.utils

import android.content.Context
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.kiranam.android.ApplicationController
import com.kiranam.android.R

object Utility {
    @JvmStatic
    fun isNetworkAvailable(context: Context): Boolean {
        return try {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            !(networkInfo == null || !networkInfo.isConnected)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    @JvmStatic
    val context: Context
        get() = ApplicationController.instance.applicationContext

    fun showSnackBar(activity: Context?, view: View?, text: String?, type: Int) {
        if (view == null || text == null || activity == null) {
            return
        }
        val snackBar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
        val txtMessage = snackBar.view.findViewById<View>(R.id.snackbar_text) as TextView
        txtMessage.setTextColor(ContextCompat.getColor(activity, R.color.white))
        if (type == 2) snackBar.view.setBackgroundColor(ContextCompat.getColor(activity, R.color.black)) else if (type == 1) snackBar.view.setBackgroundColor(ContextCompat.getColor(activity, R.color.app_snack_bar_true)) else {
            snackBar.view.setBackgroundColor(ContextCompat.getColor(activity, R.color.bold_text_color))
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
        if (type == 2) snackBar.view.setBackgroundColor(ContextCompat.getColor(activity, R.color.black)) else if (type == 1) snackBar.view.setBackgroundColor(ContextCompat.getColor(activity, R.color.app_snack_bar_true)) else {
            snackBar.view.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorAccent))
        }
        snackBar.show()
    }
}
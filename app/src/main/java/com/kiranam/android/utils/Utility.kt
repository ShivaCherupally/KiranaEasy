package com.kiranam.android.utils

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.kiranam.android.ApplicationController
import com.kiranam.android.R
import com.kiranam.android.StudentListActivity

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
}
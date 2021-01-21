package com.goodeggs.android.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.goodeggs.android.MainViewModel
import com.goodeggs.android.R
import com.goodeggs.android.bottommenu.BottomMenuNew
import com.goodeggs.android.ui.dailog.IInternetAppDialog
import com.goodeggs.android.ui.dailog.IUpdateAppDialog
import com.goodeggs.android.ui.dailog.NoInternetDialog
import com.goodeggs.android.ui.dailog.UpdateAppDialog
import com.goodeggs.android.ui.login.LoginActivity
import com.goodeggs.android.utils.AppPreferences
import com.goodeggs.android.utils.Utility


class Splashscreen : AppCompatActivity(), IUpdateAppDialog, IInternetAppDialog {
    private lateinit var viewModel: MainViewModel
    var iUpdateAppDialog: IUpdateAppDialog? = null
    var updateAppDialog: UpdateAppDialog? = null

    var nooInternetDialog: NoInternetDialog? = null
    var iInternetAppDialog: IInternetAppDialog? = null
    private val TAG = "MyFirebaseMsgService"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splashscreen)

        iUpdateAppDialog = this
        iInternetAppDialog = this

        forceUpdate()
//        flowController()

    }


    fun flowController() {
        Handler(Looper.getMainLooper()).postDelayed({

            if (!AppPreferences.isLogin) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, BottomMenuNew::class.java))
                finish()
            }
        }, 500)


    }


    override fun onResume() {
        super.onResume()
//        if (updateAppDialog != null && updateAppDialog?.isShowing!!) {
//            Toast.makeText(this, "Is showing", Toast.LENGTH_SHORT).show()
//            updateAppDialog?.show()
//        }
//        flowController()

    }

    fun forceUpdate() {
        if (Utility.checkInternetConnection(applicationContext)) {
            viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
            Log.e("versionno", Utility.getAppVersionCode(applicationContext) + "")
            viewModel.forceupdate(Utility.getAppVersionCode(applicationContext))?.observe(this, Observer { userInfo ->
                if (userInfo.get(0).success == 1) {
//                getOrderList()
                    if (userInfo.get(0).isupdate == 1) {
                        openDialog(true)
                    } else {
                        flowController()
                    }
                } else {
                    flowController()
//                    Toast.makeText(this, "Fail to cancel order", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            nonetDailog(true)
//            Toast.makeText(this, "Please check the internet connection", Toast.LENGTH_SHORT).show()
        }

    }

    fun openDialog(hardUpdate: Boolean) {
        updateAppDialog = UpdateAppDialog(this, iUpdateAppDialog, hardUpdate)
        updateAppDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        updateAppDialog!!.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        updateAppDialog!!.show()
    }

    fun nonetDailog(hardUpdate: Boolean) {
        nooInternetDialog = NoInternetDialog(this, iInternetAppDialog, hardUpdate)
        nooInternetDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        nooInternetDialog!!.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        nooInternetDialog!!.show()
    }

    override fun doLater() {
        /*  val intent = Intent(Intent.ACTION_VIEW)
          intent.data = Uri.parse("market://details?id=$packageName")
          startActivity(intent)
          finish()*/
    }

    override fun reasonReturn(reason: String?, pos: Int) {
        TODO("Not yet implemented")
    }

    override fun updateNow() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://play.google.com/store/apps/details?id=com.goodeggs.android")
        startActivity(intent)
        finish()
    }

    override fun retryClick() {
        nooInternetDialog?.cancel()
        forceUpdate()
    }

//    override fun onBackPressed() {
//        if (updateAppDialog != null && updateAppDialog?.isShowing!!) {
////            if (updateAppDialog?.isShowing!!) {
//            Toast.makeText(this, "Is showing", Toast.LENGTH_SHORT).show()
////            } else {
////                super.onBackPressed()
////            }
//        }
//    }


}




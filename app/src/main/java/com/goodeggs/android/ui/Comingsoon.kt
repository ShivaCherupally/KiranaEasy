package com.goodeggs.android.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.goodeggs.android.R

class Comingsoon : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comingsoonpage)
//        onBackPressed()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity();
        System.exit(0);
    }

//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        if ((keyCode == KeyEvent.KEYCODE_BACK))
//        {
//            finishAffinity();
//            System.exit(0);
////            return false;
//        }
//        return super.onKeyDown(keyCode, event)
//    }

}
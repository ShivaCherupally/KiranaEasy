package com.kiranam.android.utils

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.kiranam.android.R

fun Toast.createToast(context: Context, message: String, gravity: Int, duration: Int) {
    val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val layout = inflater.inflate(R.layout.customtoast, (context as Activity).findViewById<ViewGroup>(R.id.custom_toast_container))
    layout.findViewById<TextView>(R.id.text).text = message
    setGravity(gravity, 0, 0)
    setDuration(Toast.LENGTH_LONG);
    setView(layout);
    show()
}
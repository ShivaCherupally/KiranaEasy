package com.kiranam.android

import android.app.Application

class ApplicationController : Application() {
    companion object {
        lateinit var instance: ApplicationController
            private set
    }

    override fun onCreate() {
        super.onCreate()
        ApplicationController.instance = this
    }
}
package com.goodeggs.android

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import com.goodeggs.android.utils.AppPreferences

class ApplicationController : Application() {
    private lateinit var analytics: FirebaseAnalytics

    companion object {
        lateinit var instance: ApplicationController
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppPreferences.init(this)
        analytics = FirebaseAnalytics.getInstance(this)
        analytics.logEvent("buttonclick", null)
    }
}
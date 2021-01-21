package com.goodeggs.android.utils

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {
    private const val NAME = "KiranaEasy"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    //SharedPreferences variables
    private val IS_LOGIN = Pair("is_login", false)
    private val MOBILE = Pair("username", "")
    private val PASSWORD = Pair("password", "")
    private val FULLNAME = Pair("fullname", "")
    private val EMAIL = Pair("email", "")
    private val USER_ID = Pair("user_id", "")
    private val CART_COUNT = Pair("cartcount", "")
    private val CITYNAME = Pair("address", "")
    private val PINCODE = Pair("pincode", "")
    private val LANDMARK = Pair("landmark", "")
    private val DOORNO = Pair("doorno", "")
    private val IS_ADDRESS_AVAILABLE = Pair("is_address", false)

    // list of app specific preferences
    private val IS_FIRST_RUN_PREF = Pair("is_first_run", false)

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    /**
     * SharedPreferences extension function, so we won't need to call edit() and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var firstRun: Boolean
        get() = preferences.getBoolean(IS_FIRST_RUN_PREF.first, IS_FIRST_RUN_PREF.second)
        set(value) = preferences.edit {
            it.putBoolean(IS_FIRST_RUN_PREF.first, value)
        }

    //SharedPreferences variables getters/setters
    var isLogin: Boolean
        get() = preferences.getBoolean(IS_LOGIN.first, IS_LOGIN.second)
        set(value) = preferences.edit {
            it.putBoolean(IS_LOGIN.first, value)
        }

    var userId: String
        get() = preferences.getString(USER_ID.first, USER_ID.second) ?: ""
        set(value) = preferences.edit {
            it.putString(USER_ID.first, value)
        }

    var mobile: String
        get() = preferences.getString(MOBILE.first, MOBILE.second) ?: ""
        set(value) = preferences.edit {
            it.putString(MOBILE.first, value)
        }

    var fullname: String
        get() = preferences.getString(FULLNAME.first, FULLNAME.second) ?: ""
        set(value) = preferences.edit {
            it.putString(FULLNAME.first, value)
        }

    var email: String
        get() = preferences.getString(EMAIL.first, EMAIL.second) ?: ""
        set(value) = preferences.edit {
            it.putString(EMAIL.first, value)
        }

    var password: String
        get() = preferences.getString(PASSWORD.first, PASSWORD.second) ?: ""
        set(value) = preferences.edit {
            it.putString(PASSWORD.first, value)
        }

    var cartCount: String
        get() = preferences.getString(CART_COUNT.first, CART_COUNT.second) ?: ""
        set(value) = preferences.edit {
            it.putString(CART_COUNT.first, value)
        }

    var cityName: String
        get() = preferences.getString(CITYNAME.first, CITYNAME.second) ?: ""
        set(value) = preferences.edit {
            it.putString(CITYNAME.first, value)
        }
    var pincode: String
        get() = preferences.getString(PINCODE.first, PINCODE.second) ?: ""
        set(value) = preferences.edit {
            it.putString(PINCODE.first, value)
        }
    var doorno: String
        get() = preferences.getString(DOORNO.first, DOORNO.second) ?: ""
        set(value) = preferences.edit {
            it.putString(DOORNO.first, value)
        }
    var landmark: String
        get() = preferences.getString(LANDMARK.first, LANDMARK.second) ?: ""
        set(value) = preferences.edit {
            it.putString(LANDMARK.first, value)
        }

    var isAddressAvailable: Boolean
        get() = preferences.getBoolean(IS_ADDRESS_AVAILABLE.first, IS_ADDRESS_AVAILABLE.second)
        set(value) = preferences.edit {
            it.putBoolean(IS_ADDRESS_AVAILABLE.first, value)
        }

}
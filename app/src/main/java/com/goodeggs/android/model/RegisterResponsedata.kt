package com.goodeggs.android.model


data class RegisterResponsedata(
        var user_id: Int?,
        var email: String?,
        var success: Int?,
        var message: String?,
        var status: Int?

)
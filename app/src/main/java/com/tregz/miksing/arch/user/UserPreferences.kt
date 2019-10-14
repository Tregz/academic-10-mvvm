package com.tregz.miksing.arch.user

import android.content.Context
import android.content.SharedPreferences
import android.provider.BaseColumns
import com.tregz.miksing.data.DataModel

class UserPreferences(context: Context?) {

    private val pref: SharedPreferences? = context?.getSharedPreferences(SP, Context.MODE_PRIVATE)

    var id: String?
        get() = pref?.getString(BaseColumns._ID, null)
        set(value) { pref?.edit()?.putString(USER_ID, value)?.apply() }

    companion object {
        private val USER_ID = "${DataModel.User.TAG}${BaseColumns._ID}"
        const val SP = "com.tregz.miksing.shared_preferences"
    }
}
package com.diazp.catchit.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.diazp.catchit.model.Data
import com.google.gson.Gson

class SharedPref(activity: Activity) {
    val mypref = "MAIN_PREF"
    val sp: SharedPreferences
    val login = "login"
    val nama = "nama"
    val no_hp = "no_hp"
    val email = "email"
    val data = "data"

    init {
        sp = activity.getSharedPreferences(mypref, Context.MODE_PRIVATE)
    }

    fun setStatusLogin(status: Boolean) {
        sp.edit().putBoolean(login, status).apply()
    }

    fun getStatusLogin(): Boolean {
        return sp.getBoolean(login, false)
    }

    fun setData(value: Data) {
        val userData: String = Gson().toJson(value, Data::class.java)
        sp.edit().putString(data, userData).apply()
    }

    fun getData(): Data? {
        val userData: String = sp.getString(data, null) ?: return null
        return Gson().fromJson<Data>(userData, Data::class.java)
    }

    fun setString(key: String, value: String) {
        sp.edit().putString(key, value).apply()
    }

    fun getString(key: String): String {
        return sp.getString(key, "")!!
    }
}
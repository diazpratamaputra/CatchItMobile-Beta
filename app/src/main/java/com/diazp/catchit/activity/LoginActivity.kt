package com.diazp.catchit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diazp.catchit.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun viewForgotPassword(view: android.view.View) {}
    fun viewRegisterClicked(view: android.view.View) {}
}
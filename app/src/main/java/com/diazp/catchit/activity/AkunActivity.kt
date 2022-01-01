package com.diazp.catchit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diazp.catchit.R
import com.diazp.catchit.helper.SharedPref
import kotlinx.android.synthetic.main.activity_akun.*

class AkunActivity : AppCompatActivity() {
    lateinit var sp: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_akun)

        sp = SharedPref(this)

        btn_logout.setOnClickListener{
            sp.setStatusLogin(false)
        }
    }
}
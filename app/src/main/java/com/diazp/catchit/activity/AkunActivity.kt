package com.diazp.catchit.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diazp.catchit.MainActivity
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
            val intent = Intent(this@AkunActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        setData()
    }

    fun setData() {
        if (sp.getData() == null) {
            val intent = Intent(this@AkunActivity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        val user = sp.getData()!!

        tv_nama.text = user.name
        tv_no_hp.text = user.no_hp
        tv_email.text = user.email
    }
}
package com.diazp.catchit.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diazp.catchit.R
import com.diazp.catchit.helper.SharedPref
import kotlinx.android.synthetic.main.activity_masuk.*

class MasukActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masuk)

        mainButton()
    }

    private fun mainButton() {
        btn_proses_login.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btn_proses_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
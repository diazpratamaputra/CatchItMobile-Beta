package com.diazp.catchit.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.diazp.catchit.MainActivity
import com.diazp.catchit.R
import com.diazp.catchit.api.ApiConfig
import com.diazp.catchit.helper.SharedPref
import com.diazp.catchit.model.ResponseModel
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var sp: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sp = SharedPref(this)
        // ini buat ngubah value dari login : sp.setStatusLogin(true)

        btn_login.setOnClickListener {
            login()
        }
    }

    private fun login() {
        if (et_email.text.isEmpty()) {
            et_email.error = "Kolom email tidak boleh kosong."
            et_email.requestFocus()
            return
        } else if (et_password.text.isEmpty()) {
            et_password.error = "Kolom password tidak boleh kosong."
            et_password.requestFocus()
            return
        }

        pb.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.login(et_email.text.toString(), et_password.text.toString()).enqueue(object: Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                pb.visibility = View.GONE
                // Handle ketika sukses
                val respon = response.body()!!

                if (respon.status == true) {
                    // Berhasil
                    sp.setStatusLogin(true)
                    sp.setData(respon.data)
                    //Dibawah ini sudah tidak terpakai karena digantikan dengan yang diatas.
//                    sp.setString(sp.nama, respon.data.name)
//                    sp.setString(sp.no_hp, respon.data.no_hp)
//                    sp.setString(sp.email, respon.data.email)
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish() // Untuk menyelesaikan halaman activity_login
                    // onBackPressed() Untuk kembali ke halaman sebelumnya(seperti tombol back)
                    Toast.makeText(this@LoginActivity, "Selamat datang "+respon.data.name, Toast.LENGTH_SHORT).show()
                } else {
                    // Gagal
                    Toast.makeText(this@LoginActivity, "Error: "+respon.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                pb.visibility = View.GONE
                // Handle ketika gagal
                Toast.makeText(this@LoginActivity, "Error: "+t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun viewForgotPassword(view: android.view.View) {}
    fun viewRegisterClicked(view: android.view.View) {}
}
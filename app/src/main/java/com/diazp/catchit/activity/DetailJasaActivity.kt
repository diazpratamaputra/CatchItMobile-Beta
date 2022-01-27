package com.diazp.catchit.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.diazp.catchit.R
import com.diazp.catchit.helper.Helper
import com.diazp.catchit.model.Jasa
import com.diazp.catchit.room.MyDatabaseJasa
import com.diazp.catchit.util.Config
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail_jasa.*
import kotlinx.android.synthetic.main.toolbar_custom.*

class DetailJasaActivity : AppCompatActivity() {
    lateinit var jasa: Jasa
    lateinit var myDb: MyDatabaseJasa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_jasa)
        myDb = MyDatabaseJasa.getInstance(this)!! // call database
        getDetailJasa()
        mainButton()
        checkKeranjang()
    }

    fun mainButton() {
        btn_keranjang.setOnClickListener {
            val data = myDb.daoKeranjangJasa().getJasa(jasa.id_jasa)
            if (data == null) {
                insert()
            } else {
                data.jumlah_jasa += 1
                update(data)
            }
        }

        btn_favorit.setOnClickListener {
            val listJasa = myDb.daoKeranjangJasa().getAll() // get All data
            for(note :Jasa in listJasa){
                println("-----------------------")
                println(note.nama_jasa)
                println(note.harga)
                println(note.jumlah_jasa)
            }
        }

        btn_toKeranjang.setOnClickListener {
            val intent = Intent("event:keranjang")
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
            onBackPressed()
        }
    }

    fun insert() {
        CompositeDisposable().add(Observable.fromCallable { myDb.daoKeranjangJasa().insert(jasa) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                checkKeranjang()
                Log.d("respons", "data inserted")
                Toast.makeText(this, "Ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()
            })
    }

    fun update(data: Jasa) {
        CompositeDisposable().add(Observable.fromCallable { myDb.daoKeranjangJasa().update(data) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                checkKeranjang()
                Log.d("respons", "data inserted")
                Toast.makeText(this, "Ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()
            })
    }

    private fun checkKeranjang() {
        val dataKeranjang = myDb.daoKeranjangJasa().getAll()

        if(dataKeranjang.isNotEmpty()) {
            div_angka.visibility = View.VISIBLE
            tv_angka.text = dataKeranjang.size.toString()
        } else {
            div_angka.visibility = View.GONE
        }
    }

    fun getDetailJasa() {
        val data = intent.getStringExtra("extra")
        jasa = Gson().fromJson<Jasa>(data, Jasa::class.java)

        // set value
        val image = Config.baseURL + jasa.thumbnail
        Picasso.get()
            .load(image)
            .placeholder(R.drawable.jasa_catchit)
            .error(R.drawable.jasa_catchit)
            .into(iv_jasa)
        tv_nama.text = jasa.nama_jasa
        tv_deskripsi.text = jasa.deskripsi_jasa
        tv_harga.text = Helper().toRupiah(jasa.harga)

        // set toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.title = jasa.nama_jasa
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
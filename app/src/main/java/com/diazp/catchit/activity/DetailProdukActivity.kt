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
import com.diazp.catchit.model.Produk
import com.diazp.catchit.room.MyDatabaseProduk
import com.diazp.catchit.util.Config
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail_produk.*
import kotlinx.android.synthetic.main.toolbar_custom.*

class DetailProdukActivity : AppCompatActivity() {
    lateinit var produk: Produk
    lateinit var myDb: MyDatabaseProduk

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_produk)
        myDb = MyDatabaseProduk.getInstance(this)!! // call database
        getDetailProduk()
        mainButton()
        checkKeranjang()
    }

    fun mainButton() {
        btn_keranjang.setOnClickListener {
            val data = myDb.daoKeranjangProduk().getProduk(produk.id_produk)
            if (data == null) {
                insert()
            } else {
                data.jumlah_produk += 1
                update(data)
            }
        }

        btn_favorit.setOnClickListener {
            val listProduk = myDb.daoKeranjangProduk().getAll() // get All data
            for(note :Produk in listProduk){
                println("-----------------------")
                println(note.nama_produk)
                println(note.harga)
                println(note.jumlah_produk)
            }
        }

        btn_toKeranjang.setOnClickListener {
            val intent = Intent("event:keranjang")
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
            onBackPressed()
        }
    }

    fun insert() {
        CompositeDisposable().add(Observable.fromCallable { myDb.daoKeranjangProduk().insert(produk) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                checkKeranjang()
                Log.d("respons", "data inserted")
                Toast.makeText(this, "Ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()
            })
    }
    fun update(data: Produk) {
        CompositeDisposable().add(Observable.fromCallable { myDb.daoKeranjangProduk().update(data) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                checkKeranjang()
                Log.d("respons", "data inserted")
                Toast.makeText(this, "Ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()
            })
    }

    private fun checkKeranjang() {
        val dataKeranjang = myDb.daoKeranjangProduk().getAll()

        if(dataKeranjang.isNotEmpty()) {
            div_angka.visibility = View.VISIBLE
            tv_angka.text = dataKeranjang.size.toString()
        } else {
            div_angka.visibility = View.GONE
        }
    }

    fun getDetailProduk() {
        val data = intent.getStringExtra("extra")
        produk = Gson().fromJson<Produk>(data, Produk::class.java)

        // set value
        val image = Config.baseURL + produk.thumbnail
        Picasso.get()
            .load(image)
            .placeholder(R.drawable.produk_catchit)
            .error(R.drawable.produk_catchit)
            .into(iv_produk)
        tv_nama.text = produk.nama_produk
        tv_deskripsi.text = produk.deskripsi_produk
        tv_harga.text = Helper().toRupiah(produk.harga)

        // set toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.title = produk.nama_produk
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
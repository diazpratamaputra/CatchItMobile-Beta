package com.diazp.catchit.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.diazp.catchit.R
import com.diazp.catchit.helper.Helper
import com.diazp.catchit.model.Produk
import com.diazp.catchit.room.MyDatabaseProduk
import com.diazp.catchit.util.Config
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlin.collections.ArrayList

class AdapterKeranjang(var activity: Activity, var data: ArrayList<Produk>, var listener: Listeners) : RecyclerView.Adapter<AdapterKeranjang.Holder>() {
    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val ivProdukJasa = view.findViewById<ImageView>(R.id.iv_produkjasa)
        val tvNama = view.findViewById<TextView>(R.id.tv_nama)
        val tvHarga = view.findViewById<TextView>(R.id.tv_harga)
        val lyKeranjang = view.findViewById<CardView>(R.id.layout_keranjang)

        val btnTambah = view.findViewById<ImageView>(R.id.btn_tambah)
        val btnKurang = view.findViewById<ImageView>(R.id.btn_kurang)
        val btnDelete = view.findViewById<ImageView>(R.id.btn_delete)

        val checkBox = view.findViewById<CheckBox>(R.id.check_box)
        val tvJumlah = view.findViewById<TextView>(R.id.tv_jumlah)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_keranjang, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val produk = data[position]
        val harga = produk.harga

        val image = Config.baseURL + produk.thumbnail
        Picasso.get()
            .load(image)
            .placeholder(R.drawable.produk_catchit)
            .error(R.drawable.produk_catchit)
            .into(holder.ivProdukJasa)
        holder.tvNama.text = produk.nama_produk
        holder.tvHarga.text = Helper().toRupiah(produk.harga * produk.jumlah_produk)

        var jumlah = produk.jumlah_produk
        holder.tvJumlah.text = jumlah.toString()

        holder.checkBox.isChecked = produk.selected_produk
        holder.checkBox.setOnCheckedChangeListener { compoundButton, b ->
            produk.selected_produk = b
            update(produk)
        }

        holder.btnTambah.setOnClickListener {
            jumlah++

            produk.jumlah_produk = jumlah
            update(produk)
            holder.tvJumlah.text = jumlah.toString()
            holder.tvHarga.text = Helper().toRupiah((harga * jumlah))
        }

        holder.btnKurang.setOnClickListener {
            if (jumlah <= 1) return@setOnClickListener
            jumlah--

            produk.jumlah_produk = jumlah
            update(produk)
            holder.tvJumlah.text = jumlah.toString()
            holder.tvHarga.text = Helper().toRupiah((harga * jumlah))
        }

        holder.btnDelete.setOnClickListener {
            delete(produk)
            listener.onDelete(position)
        }
    }

    interface Listeners {
        fun onUpdate() {
            Log.d("onUpdate", "call this")
        }

        fun onDelete(position: Int) {
            Log.d("onDelete", "call this")
        }
    }

    fun update(data: Produk) {
        val myDb = MyDatabaseProduk.getInstance(activity)
        CompositeDisposable().add(Observable.fromCallable { myDb!!.daoKeranjangProduk().update(data) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                listener.onUpdate()
            })
    }
    fun delete(data: Produk) {
        val myDb = MyDatabaseProduk.getInstance(activity)
        CompositeDisposable().add(Observable.fromCallable { myDb!!.daoKeranjangProduk().delete(data) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {

            })
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
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
import com.diazp.catchit.model.Jasa
import com.diazp.catchit.model.Produk
import com.diazp.catchit.room.MyDatabaseJasa
import com.diazp.catchit.room.MyDatabaseProduk
import com.diazp.catchit.util.Config
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlin.collections.ArrayList

class AdapterKeranjangJasa(var activity: Activity, var data: ArrayList<Jasa>, var listener: Listeners) : RecyclerView.Adapter<AdapterKeranjangJasa.Holder>() {
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
        val jasa = data[position]
        val harga = jasa.harga

        val image = Config.baseURL + jasa.thumbnail
        Picasso.get()
            .load(image)
            .placeholder(R.drawable.jasa_catchit)
            .error(R.drawable.jasa_catchit)
            .into(holder.ivProdukJasa)
        holder.tvNama.text = jasa.nama_jasa
        holder.tvHarga.text = Helper().toRupiah(jasa.harga * jasa.jumlah_jasa)

        var jumlah = jasa.jumlah_jasa
        holder.tvJumlah.text = jumlah.toString()

        holder.checkBox.isChecked = jasa.selected_jasa
        holder.checkBox.setOnCheckedChangeListener { compoundButton, b ->
            jasa.selected_jasa = b
            update(jasa)
        }

        holder.btnTambah.setOnClickListener {
            jumlah++

            jasa.jumlah_jasa = jumlah
            update(jasa)
            holder.tvJumlah.text = jumlah.toString()
            holder.tvHarga.text = Helper().toRupiah((harga * jumlah))
        }

        holder.btnKurang.setOnClickListener {
            if (jumlah <= 1) return@setOnClickListener
            jumlah--

            jasa.jumlah_jasa = jumlah
            update(jasa)
            holder.tvJumlah.text = jumlah.toString()
            holder.tvHarga.text = Helper().toRupiah((harga * jumlah))
        }

        holder.btnDelete.setOnClickListener {
            delete(jasa)
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

    fun update(data: Jasa) {
        val myDb = MyDatabaseJasa.getInstance(activity)
        CompositeDisposable().add(Observable.fromCallable { myDb!!.daoKeranjangJasa().update(data) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                listener.onUpdate()
            })
    }
    fun delete(data: Jasa) {
        val myDb = MyDatabaseJasa.getInstance(activity)
        CompositeDisposable().add(Observable.fromCallable { myDb!!.daoKeranjangJasa().delete(data) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {

            })
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
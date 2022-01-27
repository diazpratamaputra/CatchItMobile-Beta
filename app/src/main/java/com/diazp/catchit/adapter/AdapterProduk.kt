package com.diazp.catchit.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.diazp.catchit.R
import com.diazp.catchit.activity.DetailProdukActivity
import com.diazp.catchit.helper.Helper
import com.diazp.catchit.model.Produk
import com.diazp.catchit.util.Config
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList

class AdapterProduk(var activity: Activity, var data: ArrayList<Produk>) : RecyclerView.Adapter<AdapterProduk.Holder>() {
    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val tvRating = view.findViewById<TextView>(R.id.tv_rating_produk)
        val ivProduk = view.findViewById<ImageView>(R.id.iv_produk)
        val tvNama = view.findViewById<TextView>(R.id.tv_nama_produk)
        val tvDesc = view.findViewById<TextView>(R.id.tv_desc_produk)
        val tvHarga = view.findViewById<TextView>(R.id.tv_harga_produk)
        val lyProduk = view.findViewById<CardView>(R.id.layout_produk)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvRating.text = data[position].rate.toString()
        val image = Config.baseURL + data[position].thumbnail
        Picasso.get()
            .load(image)
            .placeholder(R.drawable.produk_catchit)
            .error(R.drawable.produk_catchit)
            .into(holder.ivProduk)
        holder.tvNama.text = data[position].nama_produk
        holder.tvDesc.text = data[position].deskripsi_produk
        holder.tvHarga.text = Helper().toRupiah(data[position].harga)
        holder.lyProduk.setOnClickListener {
            val aktivitas = Intent(activity, DetailProdukActivity::class.java)
            val str = Gson().toJson(data[position], Produk::class.java)
            aktivitas.putExtra("extra", str)
            activity.startActivity(aktivitas)
        }
    }
    override fun getItemCount(): Int {
        return data.size
    }
}
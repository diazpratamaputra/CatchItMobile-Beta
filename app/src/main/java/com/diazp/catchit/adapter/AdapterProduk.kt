package com.diazp.catchit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diazp.catchit.R
import com.diazp.catchit.model.Produk

class AdapterProduk(var data: ArrayList<Produk>) : RecyclerView.Adapter<AdapterProduk.Holder>() {
    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val tvRating = view.findViewById<TextView>(R.id.tv_rating_produk)
        val ivProduk = view.findViewById<ImageView>(R.id.iv_produk)
        val tvNama = view.findViewById<TextView>(R.id.tv_nama_produk)
        val tvDesc = view.findViewById<TextView>(R.id.tv_desc_produk)
        val tvHarga = view.findViewById<TextView>(R.id.tv_harga_produk)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvRating.text = data[position].rate.toString()
//        holder.ivProduk.setImageResource(data[position].foto)
        holder.tvNama.text = data[position].nama_produk
        holder.tvDesc.text = data[position].deskripsi_produk
        holder.tvHarga.text = data[position].harga.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
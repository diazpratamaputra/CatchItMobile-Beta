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
        val tvRating = view.findViewById<TextView>(R.id.tv_rating)
        val ivProduk = view.findViewById<ImageView>(R.id.iv_produk)
        val tvNama = view.findViewById<TextView>(R.id.tv_nama)
        val tvDesc = view.findViewById<TextView>(R.id.tv_desc)
        val tvTime = view.findViewById<TextView>(R.id.tv_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvRating.text = data[position].rating
        holder.ivProduk.setImageResource(data[position].gambar)
        holder.tvNama.text = data[position].nama
        holder.tvDesc.text = data[position].desc
        holder.tvTime.text = data[position].time
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
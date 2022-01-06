package com.diazp.catchit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diazp.catchit.R
import com.diazp.catchit.model.Jasa

class AdapterJasa(var data: ArrayList<Jasa>) : RecyclerView.Adapter<AdapterJasa.Holder>() {
    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val tvRating = view.findViewById<TextView>(R.id.tv_rating_jasa)
        val ivProduk = view.findViewById<ImageView>(R.id.iv_jasa)
        val tvNama = view.findViewById<TextView>(R.id.tv_nama_jasa)
        val tvDesc = view.findViewById<TextView>(R.id.tv_desc_jasa)
        val tvHarga = view.findViewById<TextView>(R.id.tv_harga_jasa)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_jasa, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvRating.text = data[position].rate.toString()
//        holder.ivProduk.setImageResource(data[position].foto)
        holder.tvNama.text = data[position].nama_jasa
        holder.tvDesc.text = data[position].deskripsi_jasa
        holder.tvHarga.text = data[position].harga.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
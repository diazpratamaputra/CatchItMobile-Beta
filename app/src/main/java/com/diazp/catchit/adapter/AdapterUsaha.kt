package com.diazp.catchit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diazp.catchit.R
import com.diazp.catchit.model.Usaha

class AdapterUsaha(var data: ArrayList<Usaha>) : RecyclerView.Adapter<AdapterUsaha.Holder>() {
    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val ivProduk = view.findViewById<ImageView>(R.id.iv_usaha)
        val tvNama = view.findViewById<TextView>(R.id.tv_nama_usaha)
        val tvDesc = view.findViewById<TextView>(R.id.tv_desc_usaha)
        val tvJam = view.findViewById<TextView>(R.id.tv_jam_operasional)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_usaha, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
//        holder.ivProduk.setImageResource(data[position].gambar)
        holder.tvNama.text = data[position].nama_usaha
        holder.tvDesc.text = data[position].deskripsi
        holder.tvJam.text = data[position].jam_operasional
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
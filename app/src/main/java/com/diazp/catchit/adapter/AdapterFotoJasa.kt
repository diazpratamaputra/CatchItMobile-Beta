package com.diazp.catchit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diazp.catchit.R
import com.diazp.catchit.model.FotoJasa
import com.squareup.picasso.Picasso

class AdapterFotoJasa(var data: ArrayList<FotoJasa>) : RecyclerView.Adapter<AdapterFotoJasa.Holder>() {
    class Holder(view: View) : RecyclerView.ViewHolder(view) {
//        val tvRating = view.findViewById<TextView>(R.id.tv_rating_produk)
//        val ivProduk = view.findViewById<ImageView>(R.id.iv_produk)
//        val tvNama = view.findViewById<TextView>(R.id.tv_nama_produk)
//        val tvDesc = view.findViewById<TextView>(R.id.tv_desc_produk)
//        val tvHarga = view.findViewById<TextView>(R.id.tv_harga_produk)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
//        holder.tvRating.text = data[position].rate.toString()
////        holder.ivProduk.setImageResource(data[position].foto)
//        val image = "http://192.168.43.158/1/public/" + data[position].profile_photo_url
//        Picasso.get()
//            .load(image)
//            .resize(160,160)
//            .placeholder(R.drawable.produk_catchit)
//            .error(R.drawable.produk_catchit)
//            .into(holder.ivProduk)
//        holder.tvNama.text = data[position].nama_produk
//        holder.tvDesc.text = data[position].deskripsi_produk
//        holder.tvHarga.text = "Rp." + data[position].harga.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
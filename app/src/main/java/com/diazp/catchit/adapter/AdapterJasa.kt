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
import com.diazp.catchit.activity.DetailJasaActivity
import com.diazp.catchit.helper.Helper
import com.diazp.catchit.model.Jasa
import com.diazp.catchit.util.Config
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList

class AdapterJasa(var activity: Activity, var data: ArrayList<Jasa>) : RecyclerView.Adapter<AdapterJasa.Holder>() {
    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val tvRating = view.findViewById<TextView>(R.id.tv_rating_jasa)
        val ivJasa = view.findViewById<ImageView>(R.id.iv_jasa)
        val tvNama = view.findViewById<TextView>(R.id.tv_nama_jasa)
        val tvDesc = view.findViewById<TextView>(R.id.tv_desc_jasa)
        val tvHarga = view.findViewById<TextView>(R.id.tv_harga_jasa)
        val lyJasa = view.findViewById<CardView>(R.id.layout_jasa)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_jasa, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvRating.text = data[position].rate.toString()
//        holder.ivProduk.setImageResource(data[position].foto)
        val image = Config.baseURL + data[position].thumbnail
        Picasso.get()
            .load(image)
            .placeholder(R.drawable.jasa_catchit)
            .error(R.drawable.jasa_catchit)
            .into(holder.ivJasa)
        holder.tvNama.text = data[position].nama_jasa
        holder.tvDesc.text = data[position].deskripsi_jasa
        holder.tvHarga.text = Helper().toRupiah(data[position].harga)
        holder.lyJasa.setOnClickListener {
            val aktivitas = Intent(activity, DetailJasaActivity::class.java)
            val str = Gson().toJson(data[position], Jasa::class.java)
            aktivitas.putExtra("extra", str)
            activity.startActivity(aktivitas)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
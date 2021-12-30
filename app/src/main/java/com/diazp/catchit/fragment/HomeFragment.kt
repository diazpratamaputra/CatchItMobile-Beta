package com.diazp.catchit.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.diazp.catchit.R
import com.diazp.catchit.adapter.AdapterCarousel
import com.diazp.catchit.adapter.AdapterProduk
import com.diazp.catchit.model.Produk

class HomeFragment : Fragment() {
    lateinit var vpCarousel: ViewPager
    lateinit var rvRiwayat: RecyclerView
    lateinit var rvFavorit: RecyclerView
    lateinit var rvPilihan: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        vpCarousel = view.findViewById(R.id.vp_carousel)
        rvRiwayat = view.findViewById(R.id.rv_riwayat)
        rvFavorit = view.findViewById(R.id.rv_favorit)
        rvPilihan = view.findViewById(R.id.rv_pilihan)

        val arrayCarousel = ArrayList<Int>()
        arrayCarousel.add(R.drawable.carousel_1)
        arrayCarousel.add(R.drawable.carousel_2)
        arrayCarousel.add(R.drawable.carousel_3)

        val adapterCarousel = AdapterCarousel(arrayCarousel, activity)
        vpCarousel.adapter = adapterCarousel

        val lmRiwayat = LinearLayoutManager(activity)
        lmRiwayat.orientation = LinearLayoutManager.HORIZONTAL
        val lmFavorit = LinearLayoutManager(activity)
        lmFavorit.orientation = LinearLayoutManager.HORIZONTAL
        val lmPilihan = LinearLayoutManager(activity)
        lmPilihan.orientation = LinearLayoutManager.HORIZONTAL

        rvRiwayat.adapter = AdapterProduk(arrayRiwayat)
        rvRiwayat.layoutManager = lmRiwayat

        rvFavorit.adapter = AdapterProduk(arrayFavorit)
        rvFavorit.layoutManager = lmFavorit

        rvPilihan.adapter = AdapterProduk(arrayPilihan)
        rvPilihan.layoutManager = lmPilihan

        return view
    }

    val arrayRiwayat: ArrayList<Produk>get() {
        val alProduk = ArrayList<Produk>()
        val p1 = Produk()
        p1.rating = "4.8"
        p1.gambar = R.drawable.sampleproduk_1
        p1.nama = "Dahari Cafe"
        p1.desc = "Deskripsi Singkat Mengenai Tempat"
        p1.time = "10.00 - 21.00"

        val p2 = Produk()
        p2.rating = "4.8"
        p2.gambar = R.drawable.sampleproduk_1
        p2.nama = "Dahari Cafe"
        p2.desc = "Deskripsi Singkat Mengenai Tempat"
        p2.time = "10.00 - 21.00"

        val p3 = Produk()
        p3.rating = "4.8"
        p3.gambar = R.drawable.sampleproduk_1
        p3.nama = "Dahari Cafe"
        p3.desc = "Deskripsi Singkat Mengenai Tempat"
        p3.time = "10.00 - 21.00"

        val p4 = Produk()
        p4.rating = "4.8"
        p4.gambar = R.drawable.sampleproduk_1
        p4.nama = "Dahari Cafe"
        p4.desc = "Deskripsi Singkat Mengenai Tempat"
        p4.time = "10.00 - 21.00"

        val p5 = Produk()
        p5.rating = "4.8"
        p5.gambar = R.drawable.sampleproduk_1
        p5.nama = "Dahari Cafe"
        p5.desc = "Deskripsi Singkat Mengenai Tempat"
        p5.time = "10.00 - 21.00"

        alProduk.add(p1)
        alProduk.add(p2)
        alProduk.add(p3)
        alProduk.add(p4)
        alProduk.add(p5)

        return alProduk
    }
    val arrayFavorit: ArrayList<Produk>get() {
        val alProduk = ArrayList<Produk>()
        val p1 = Produk()
        p1.rating = "4.8"
        p1.gambar = R.drawable.sampleproduk_1
        p1.nama = "Dahari Cafe"
        p1.desc = "Deskripsi Singkat Mengenai Tempat"
        p1.time = "10.00 - 21.00"

        val p2 = Produk()
        p2.rating = "4.8"
        p2.gambar = R.drawable.sampleproduk_1
        p2.nama = "Dahari Cafe"
        p2.desc = "Deskripsi Singkat Mengenai Tempat"
        p2.time = "10.00 - 21.00"

        val p3 = Produk()
        p3.rating = "4.8"
        p3.gambar = R.drawable.sampleproduk_1
        p3.nama = "Dahari Cafe"
        p3.desc = "Deskripsi Singkat Mengenai Tempat"
        p3.time = "10.00 - 21.00"

        val p4 = Produk()
        p4.rating = "4.8"
        p4.gambar = R.drawable.sampleproduk_1
        p4.nama = "Dahari Cafe"
        p4.desc = "Deskripsi Singkat Mengenai Tempat"
        p4.time = "10.00 - 21.00"

        val p5 = Produk()
        p5.rating = "4.8"
        p5.gambar = R.drawable.sampleproduk_1
        p5.nama = "Dahari Cafe"
        p5.desc = "Deskripsi Singkat Mengenai Tempat"
        p5.time = "10.00 - 21.00"

        alProduk.add(p1)
        alProduk.add(p2)
        alProduk.add(p3)
        alProduk.add(p4)
        alProduk.add(p5)

        return alProduk
    }
    val arrayPilihan: ArrayList<Produk>get() {
        val alProduk = ArrayList<Produk>()
        val p1 = Produk()
        p1.rating = "4.8"
        p1.gambar = R.drawable.sampleproduk_1
        p1.nama = "Dahari Cafe"
        p1.desc = "Deskripsi Singkat Mengenai Tempat"
        p1.time = "10.00 - 21.00"

        val p2 = Produk()
        p2.rating = "4.8"
        p2.gambar = R.drawable.sampleproduk_1
        p2.nama = "Dahari Cafe"
        p2.desc = "Deskripsi Singkat Mengenai Tempat"
        p2.time = "10.00 - 21.00"

        val p3 = Produk()
        p3.rating = "4.8"
        p3.gambar = R.drawable.sampleproduk_1
        p3.nama = "Dahari Cafe"
        p3.desc = "Deskripsi Singkat Mengenai Tempat"
        p3.time = "10.00 - 21.00"

        val p4 = Produk()
        p4.rating = "4.8"
        p4.gambar = R.drawable.sampleproduk_1
        p4.nama = "Dahari Cafe"
        p4.desc = "Deskripsi Singkat Mengenai Tempat"
        p4.time = "10.00 - 21.00"

        val p5 = Produk()
        p5.rating = "4.8"
        p5.gambar = R.drawable.sampleproduk_1
        p5.nama = "Dahari Cafe"
        p5.desc = "Deskripsi Singkat Mengenai Tempat"
        p5.time = "10.00 - 21.00"

        alProduk.add(p1)
        alProduk.add(p2)
        alProduk.add(p3)
        alProduk.add(p4)
        alProduk.add(p5)

        return alProduk
    }
}
package com.diazp.catchit.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.diazp.catchit.R
import com.diazp.catchit.activity.AkunActivity
import com.diazp.catchit.activity.MasukActivity
import com.diazp.catchit.adapter.AdapterCarousel
import com.diazp.catchit.adapter.AdapterJasa
import com.diazp.catchit.adapter.AdapterProduk
import com.diazp.catchit.adapter.AdapterUsaha
import com.diazp.catchit.api.ApiConfig
import com.diazp.catchit.helper.SharedPref
import com.diazp.catchit.model.Jasa
import com.diazp.catchit.model.Produk
import com.diazp.catchit.model.ResponseModel
import com.diazp.catchit.model.Usaha
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(), View.OnClickListener {
    lateinit var vpCarousel: ViewPager
    lateinit var rvProduk: RecyclerView
    lateinit var rvJasa: RecyclerView
    lateinit var rvUsaha: RecyclerView
    lateinit var rvRiwayat: RecyclerView
    lateinit var ivUserProfile: CircleImageView
    private lateinit var sp: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        init(view)
        getData()

        ivUserProfile = view.findViewById(R.id.iv_userProfile)
        ivUserProfile.setOnClickListener(this)

        val ini = activity
        sp = SharedPref(ini!!)

        return view
    }

    fun init(view: View) {
        vpCarousel = view.findViewById(R.id.vp_carousel)
        rvProduk = view.findViewById(R.id.rv_produk)
        rvJasa = view.findViewById(R.id.rv_jasa)
        rvUsaha = view.findViewById(R.id.rv_usaha)
        rvRiwayat = view.findViewById(R.id.rv_riwayat)
    }

    fun displayData() {
        val arrayCarousel = ArrayList<Int>()
        arrayCarousel.add(R.drawable.carousel_1)
        arrayCarousel.add(R.drawable.carousel_2)
        arrayCarousel.add(R.drawable.carousel_3)

        val adapterCarousel = AdapterCarousel(arrayCarousel, activity)
        vpCarousel.adapter = adapterCarousel

        val lmProduk = LinearLayoutManager(activity)
        lmProduk.orientation = LinearLayoutManager.HORIZONTAL
        val lmJasa = LinearLayoutManager(activity)
        lmJasa.orientation = LinearLayoutManager.HORIZONTAL
        val lmUsaha = LinearLayoutManager(activity)
        lmUsaha.orientation = LinearLayoutManager.HORIZONTAL
        val lmRiwayat = LinearLayoutManager(activity)
        lmRiwayat.orientation = LinearLayoutManager.HORIZONTAL

        rvProduk.adapter = AdapterProduk(requireActivity(), listProduk)
        rvProduk.layoutManager = lmProduk

        rvJasa.adapter = AdapterJasa(requireActivity(), listJasa)
        rvJasa.layoutManager = lmJasa

        rvUsaha.adapter = AdapterUsaha(listUsaha)
        rvUsaha.layoutManager = lmUsaha

//        rvRiwayat.adapter = AdapterProduk(listProdukJasa)
//        rvRiwayat.layoutManager = lmRiwayat
    }

    private var listProduk: ArrayList<Produk> = ArrayList()
    private var listJasa: ArrayList<Jasa> = ArrayList()
    private var listUsaha: ArrayList<Usaha> = ArrayList()
    fun getData() {
        ApiConfig.instanceRetrofit.getData().enqueue(object:
            Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                val res = response.body()
                if (res!!.status == true) {
                    listProduk = res.produk
                    listJasa = res.jasa
                    listUsaha = res.usaha
                    displayData()
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {

            }

        })
    }

//    val arrayProduk: ArrayList<Produk>
//        get() {
//            val alProduk = ArrayList<Produk>()
//            val p1 = Produk()
//            p1.rating = "4.8"
//            p1.gambar = R.drawable.sampleproduk_1
//            p1.nama = "Dahari Cafe"
//            p1.desc = "Deskripsi Singkat Mengenai Tempat"
//            p1.harga = 50000
//
//            val p2 = Produk()
//            p2.rating = "4.8"
//            p2.gambar = R.drawable.sampleproduk_1
//            p2.nama = "Dahari Cafe"
//            p2.desc = "Deskripsi Singkat Mengenai Tempat"
//            p2.harga = 50000
//
//            val p3 = Produk()
//            p3.rating = "4.8"
//            p3.gambar = R.drawable.sampleproduk_1
//            p3.nama = "Dahari Cafe"
//            p3.desc = "Deskripsi Singkat Mengenai Tempat"
//            p3.harga = 50000
//
//            val p4 = Produk()
//            p4.rating = "4.8"
//            p4.gambar = R.drawable.sampleproduk_1
//            p4.nama = "Dahari Cafe"
//            p4.desc = "Deskripsi Singkat Mengenai Tempat"
//            p4.harga = 50000
//
//            val p5 = Produk()
//            p5.rating = "4.8"
//            p5.gambar = R.drawable.sampleproduk_1
//            p5.nama = "Dahari Cafe"
//            p5.desc = "Deskripsi Singkat Mengenai Tempat"
//            p5.harga = 50000
//
//            alProduk.add(p1)
//            alProduk.add(p2)
//            alProduk.add(p3)
//            alProduk.add(p4)
//            alProduk.add(p5)
//
//            return alProduk
//        }
//    val arrayJasa: ArrayList<Produk>
//        get() {
//            val alProduk = ArrayList<Produk>()
//            val p1 = Produk()
//            p1.rating = "4.8"
//            p1.gambar = R.drawable.sampleproduk_1
//            p1.nama = "Dahari Cafe"
//            p1.desc = "Deskripsi Singkat Mengenai Tempat"
//            p1.harga = 50000
//
//            val p2 = Produk()
//            p2.rating = "4.8"
//            p2.gambar = R.drawable.sampleproduk_1
//            p2.nama = "Dahari Cafe"
//            p2.desc = "Deskripsi Singkat Mengenai Tempat"
//            p2.harga = 50000
//
//            val p3 = Produk()
//            p3.rating = "4.8"
//            p3.gambar = R.drawable.sampleproduk_1
//            p3.nama = "Dahari Cafe"
//            p3.desc = "Deskripsi Singkat Mengenai Tempat"
//            p3.harga = 50000
//
//            val p4 = Produk()
//            p4.rating = "4.8"
//            p4.gambar = R.drawable.sampleproduk_1
//            p4.nama = "Dahari Cafe"
//            p4.desc = "Deskripsi Singkat Mengenai Tempat"
//            p4.harga = 50000
//
//            val p5 = Produk()
//            p5.rating = "4.8"
//            p5.gambar = R.drawable.sampleproduk_1
//            p5.nama = "Dahari Cafe"
//            p5.desc = "Deskripsi Singkat Mengenai Tempat"
//            p5.harga = 50000
//
//            alProduk.add(p1)
//            alProduk.add(p2)
//            alProduk.add(p3)
//            alProduk.add(p4)
//            alProduk.add(p5)
//
//            return alProduk
//        }
//    val arrayUsaha: ArrayList<Usaha>
//        get() {
//            val alUsaha = ArrayList<Usaha>()
//            val p1 = Usaha()
//            p1.gambar = R.drawable.sampleproduk_1
//            p1.nama = "Dahari Cafe"
//            p1.desc = "Deskripsi Singkat Mengenai Tempat"
//            p1.jam = "10.00 - 21.00"
//
//            val p2 = Usaha()
//            p2.gambar = R.drawable.sampleproduk_1
//            p2.nama = "Dahari Cafe"
//            p2.desc = "Deskripsi Singkat Mengenai Tempat"
//            p2.jam = "10.00 - 21.00"
//
//            val p3 = Usaha()
//            p3.gambar = R.drawable.sampleproduk_1
//            p3.nama = "Dahari Cafe"
//            p3.desc = "Deskripsi Singkat Mengenai Tempat"
//            p3.jam = "10.00 - 21.00"
//
//            val p4 = Usaha()
//            p4.gambar = R.drawable.sampleproduk_1
//            p4.nama = "Dahari Cafe"
//            p4.desc = "Deskripsi Singkat Mengenai Tempat"
//            p4.jam = "10.00 - 21.00"
//
//            val p5 = Usaha()
//            p5.gambar = R.drawable.sampleproduk_1
//            p5.nama = "Dahari Cafe"
//            p5.desc = "Deskripsi Singkat Mengenai Tempat"
//            p5.jam = "10.00 - 21.00"
//
//            alUsaha.add(p1)
//            alUsaha.add(p2)
//            alUsaha.add(p3)
//            alUsaha.add(p4)
//            alUsaha.add(p5)
//
//            return alUsaha
//        }
//    val arrayRiwayat: ArrayList<Produk>
//        get() {
//            val alProduk = ArrayList<Produk>()
//            val p1 = Produk()
//            p1.rating = "4.8"
//            p1.gambar = R.drawable.sampleproduk_1
//            p1.nama = "Dahari Cafe"
//            p1.desc = "Deskripsi Singkat Mengenai Tempat"
//            p1.harga = 50000
//
//            val p2 = Produk()
//            p2.rating = "4.8"
//            p2.gambar = R.drawable.sampleproduk_1
//            p2.nama = "Dahari Cafe"
//            p2.desc = "Deskripsi Singkat Mengenai Tempat"
//            p2.harga = 50000
//
//            val p3 = Produk()
//            p3.rating = "4.8"
//            p3.gambar = R.drawable.sampleproduk_1
//            p3.nama = "Dahari Cafe"
//            p3.desc = "Deskripsi Singkat Mengenai Tempat"
//            p3.harga = 50000
//
//            val p4 = Produk()
//            p4.rating = "4.8"
//            p4.gambar = R.drawable.sampleproduk_1
//            p4.nama = "Dahari Cafe"
//            p4.desc = "Deskripsi Singkat Mengenai Tempat"
//            p4.harga = 50000
//
//            val p5 = Produk()
//            p5.rating = "4.8"
//            p5.gambar = R.drawable.sampleproduk_1
//            p5.nama = "Dahari Cafe"
//            p5.desc = "Deskripsi Singkat Mengenai Tempat"
//            p5.harga = 50000
//
//            alProduk.add(p1)
//            alProduk.add(p2)
//            alProduk.add(p3)
//            alProduk.add(p4)
//            alProduk.add(p5)
//
//            return alProduk
//        }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.iv_userProfile -> {
                if (sp.getStatusLogin()) {
                    val intent = Intent(getActivity(), AkunActivity::class.java)
                    getActivity()?.startActivity(intent)
                } else {
                    val intent = Intent(getActivity(), MasukActivity::class.java)
                    getActivity()?.startActivity(intent)
                }
            }
        }
    }
}
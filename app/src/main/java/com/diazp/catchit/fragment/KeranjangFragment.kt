package com.diazp.catchit.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diazp.catchit.R
import com.diazp.catchit.adapter.AdapterKeranjangJasa
import com.diazp.catchit.adapter.AdapterKeranjang
import com.diazp.catchit.helper.Helper
import com.diazp.catchit.model.Jasa
import com.diazp.catchit.model.Produk
import com.diazp.catchit.room.MyDatabaseJasa
import com.diazp.catchit.room.MyDatabaseProduk

class KeranjangFragment : Fragment() {
    lateinit var myDbP: MyDatabaseProduk
    lateinit var myDbJ: MyDatabaseJasa
    var isScrollEnabled: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myDbP = MyDatabaseProduk.getInstance(requireActivity())!!
        myDbJ = MyDatabaseJasa.getInstance(requireActivity())!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_keranjang, container, false)
        init(view)
        mainButton()
        return view
    }
    lateinit var adapter: AdapterKeranjang
    lateinit var adapterJasa: AdapterKeranjangJasa
    var listProduk = ArrayList<Produk>()
    var listJasa = ArrayList<Jasa>()
    private fun displayData() {
        listProduk = myDbP!!.daoKeranjangProduk().getAll() as ArrayList
        val lmProduk = object : LinearLayoutManager(activity) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        lmProduk.orientation = LinearLayoutManager.VERTICAL

        listJasa = myDbJ!!.daoKeranjangJasa().getAll() as ArrayList
        val lmJasa = object : LinearLayoutManager(activity) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        lmJasa.orientation = LinearLayoutManager.VERTICAL

        adapter = AdapterKeranjang(requireActivity(), listProduk, object: AdapterKeranjang.Listeners{
            override fun onUpdate() {
                super.onUpdate()
                hitungTotal()
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDelete(position: Int) {
                super.onDelete(position)
                listProduk.removeAt(position)
                adapter.notifyDataSetChanged()
                hitungTotal()
            }
        })
        adapterJasa = AdapterKeranjangJasa(requireActivity(), listJasa, object: AdapterKeranjangJasa.Listeners{
            override fun onUpdate() {
                super.onUpdate()
                hitungTotal()
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDelete(position: Int) {
                super.onDelete(position)
                listJasa.removeAt(position)
                adapterJasa.notifyDataSetChanged()
                hitungTotal()
            }
        })

        rvProduk.adapter = adapter
        rvProduk.layoutManager = lmProduk

        rvJasa.adapter = adapterJasa
        rvJasa.layoutManager = lmJasa
    }

    fun hitungTotal() {
        val listProduk = myDbP!!.daoKeranjangProduk().getAll() as ArrayList
        val listJasa = myDbJ!!.daoKeranjangJasa().getAll() as ArrayList
        var totalHarga = 0
        var totalHargaProduk = 0
        var totalHargaJasa = 0
        var isSelectedAll = true

        for (produk in listProduk) {
            if (produk.selected_produk) {
                totalHargaProduk += (produk.harga * produk.jumlah_produk)
            } else {
                isSelectedAll = false
            }
        }
        for (jasa in listJasa) {
            if (jasa.selected_jasa) {
                totalHargaJasa += (jasa.harga * jasa.jumlah_jasa)
            } else {
                isSelectedAll = false
            }
        }

        totalHarga = totalHargaProduk + totalHargaJasa

        cbSelectAll.isChecked = isSelectedAll
        tvTotal.text = Helper().toRupiah(totalHarga)
    }

    private fun mainButton() {
        btnDelete.setOnClickListener {

        }
        btnBayar.setOnClickListener {

        }
        cbSelectAll.setOnClickListener {
            for (p in listProduk.indices) {
                val produk = listProduk[p]
                produk.selected_produk = cbSelectAll.isChecked
                listProduk[p] = produk
            }
            for (j in listJasa.indices) {
                val jasa = listJasa[j]
                jasa.selected_jasa = cbSelectAll.isChecked
                listJasa[j] = jasa
            }
            adapter.notifyDataSetChanged()
            adapterJasa.notifyDataSetChanged()
        }
    }

    lateinit var btnDelete: ImageView
    lateinit var rvProduk: RecyclerView
    lateinit var rvJasa: RecyclerView
    lateinit var tvTotal: TextView
    lateinit var btnBayar: TextView
    lateinit var cbSelectAll: CheckBox
    private fun init(view: View) {
        btnDelete = view.findViewById(R.id.btn_delete)
        rvProduk = view.findViewById(R.id.rv_produk)
        rvJasa = view.findViewById(R.id.rv_jasa)
        tvTotal = view.findViewById(R.id.tv_total)
        btnBayar = view.findViewById(R.id.btn_bayar)
        cbSelectAll = view.findViewById(R.id.cb_selectAllProduk)
    }

    override fun onResume() {
        displayData()
        hitungTotal()
        super.onResume()
    }
}
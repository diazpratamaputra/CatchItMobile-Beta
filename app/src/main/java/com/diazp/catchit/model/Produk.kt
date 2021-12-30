package com.diazp.catchit.model

import java.io.Serializable

class Produk : Serializable {
    lateinit var rating: String
    lateinit var nama: String
    lateinit var desc: String
    lateinit var time: String
    var gambar: Int = 0
}
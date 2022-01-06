package com.diazp.catchit.model

class ResponseModel {
    var status = false
    lateinit var message: String
    var data = Data()
    var produk: ArrayList<Produk> = ArrayList()
    var jasa: ArrayList<Jasa> = ArrayList()
    var usaha: ArrayList<Usaha> = ArrayList()
}
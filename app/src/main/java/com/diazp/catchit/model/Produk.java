package com.diazp.catchit.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "keranjang_produk")
public class Produk implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idTBL")
    public  int idTBL;

    public int id_produk;
    public int usaha_id_usaha;
    public String nama_produk;
    public String kategori_produk;
    public String deskripsi_produk;
    public int harga;
    public String thumbnail;
    public float rate;
    public String created_at;
    public String updated_at;
    public String profile_photo_url;
//    public ArrayList<FotoProduk> foto = new ArrayList<FotoProduk>();
    public int jumlah_produk = 1;
    public boolean selected_produk = true;
}

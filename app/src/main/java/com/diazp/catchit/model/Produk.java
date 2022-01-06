package com.diazp.catchit.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Produk implements Serializable {
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
    public ArrayList<FotoProduk> foto;
}

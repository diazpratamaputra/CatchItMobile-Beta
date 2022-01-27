package com.diazp.catchit.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "keranjang_jasa")
public class Jasa implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idTBL")
    public  int idTBL;

    public int id_jasa;
    public int usaha_id_usaha;
    public String nama_jasa;
    public String kategori_jasa;
    public String deskripsi_jasa;
    public int harga;
    public String thumbnail;
    public float rate;
    public String created_at;
    public String updated_at;
    public String profile_photo_url;
//    public ArrayList<FotoJasa> foto;
    public int jumlah_jasa = 1;
    public boolean selected_jasa;
}

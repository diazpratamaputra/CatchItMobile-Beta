package com.diazp.catchit.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Jasa implements Serializable {
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
    public ArrayList<FotoJasa> foto;
}
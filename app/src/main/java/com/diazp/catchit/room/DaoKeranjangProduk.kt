package com.diazp.catchit.room

import androidx.room.*
import com.diazp.catchit.model.Produk

@Dao
interface DaoKeranjangProduk {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: Produk)

    @Delete
    fun delete(data: Produk)

    @Update
    fun update(data: Produk): Int

    @Query("SELECT * from keranjang_produk ORDER BY id_produk ASC")
    fun getAll(): List<Produk>

    @Query("SELECT * FROM keranjang_produk WHERE id_produk = :id LIMIT 1")
    fun getProduk(id: Int): Produk

    @Query("DELETE FROM keranjang_produk")
    fun deleteAll(): Int
}
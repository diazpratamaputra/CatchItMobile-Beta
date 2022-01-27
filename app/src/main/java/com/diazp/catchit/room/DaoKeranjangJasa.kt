package com.diazp.catchit.room

import androidx.room.*
import com.diazp.catchit.model.Jasa
import com.diazp.catchit.model.Produk

@Dao
interface DaoKeranjangJasa {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: Jasa)

    @Delete
    fun delete(data: Jasa)

    @Update
    fun update(data: Jasa): Int

    @Query("SELECT * from keranjang_jasa ORDER BY id_jasa ASC")
    fun getAll(): List<Jasa>

    @Query("SELECT * FROM keranjang_jasa WHERE id_jasa = :id LIMIT 1")
    fun getJasa(id: Int): Jasa

    @Query("DELETE FROM keranjang_jasa")
    fun deleteAll(): Int
}
package com.diazp.catchit.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.diazp.catchit.model.Produk

@Database(entities = [Produk::class] /* List model Ex:NoteModel */, version = 1)
abstract class MyDatabaseProduk : RoomDatabase() {
    abstract fun daoKeranjangProduk(): DaoKeranjangProduk // DaoKeranjang

    companion object {
        private var INSTANCE: MyDatabaseProduk? = null

        fun getInstance(context: Context): MyDatabaseProduk? {
            if (INSTANCE == null) {
                synchronized(MyDatabaseProduk::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabaseProduk::class.java, "DBProdukB" // Database Name
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
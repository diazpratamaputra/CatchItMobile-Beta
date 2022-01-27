package com.diazp.catchit.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.diazp.catchit.model.Jasa

@Database(entities = [Jasa::class] /* List model Ex:NoteModel */, version = 1)
abstract class MyDatabaseJasa : RoomDatabase() {
    abstract fun daoKeranjangJasa(): DaoKeranjangJasa // DaoKeranjang

    companion object {
        private var INSTANCE: MyDatabaseJasa? = null

        fun getInstance(context: Context): MyDatabaseJasa? {
            if (INSTANCE == null) {
                synchronized(MyDatabaseJasa::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabaseJasa::class.java, "DBJasaB" // Database Name
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
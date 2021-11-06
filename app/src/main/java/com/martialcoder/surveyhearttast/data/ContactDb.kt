package com.martialcoder.surveyhearttast.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by suraj on 06/11/2021.
 */
@Database(entities = [(Contact::class)], version = 1, exportSchema = false)
abstract class ContactDb : RoomDatabase() {
    companion object {
        private var INSTANCE: ContactDb? = null
        fun getDataBase(context: Context): ContactDb {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, ContactDb::class.java, "contacts-db")
                    .allowMainThreadQueries().build()
            }
            return INSTANCE as ContactDb
        }
    }

    abstract fun daoContact(): DaoContact
}
package com.martialcoder.surveyhearttast.data

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by suraj on 06/11/2021.
 */
@Dao
interface DaoContact {
    @Query("select * from contacts")
    fun getAllContacts(): LiveData<List<Contact>>

    @Query("select * from contacts where idContact in (:id)")
    fun getContactById(id: Int): Contact

    @Query("delete from contacts")
    fun deleteAllContacts()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contact: Contact)

    @Update
    fun updateContact(contact: Contact)

    @Delete
    fun deleteContact(contact: Contact)
}
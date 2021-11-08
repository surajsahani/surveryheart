package com.martialcoder.surveyhearttast.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by suraj on 06/11/2021.
 */
@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idContact")
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "number")
    var number: String = ""  ,

    @ColumnInfo(name = "business")
    var business: String = "business",

    @ColumnInfo(name = "customer")
    var customer: String = "customer",

)
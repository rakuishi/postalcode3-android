package com.rakuishi.postalcode3.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostalCodeDao {

    @Query("SELECT * FROM postal_codes WHERE prefecture LIKE :prefecture")
    fun findByPrefecture(prefecture: String): List<PostalCode>

    @Insert
    fun insert(postalCode: PostalCode)
}
package com.rakuishi.postalcode3.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostalCodeDao {

    @Query("SELECT * FROM postal_codes WHERE prefecture LIKE :prefecture")
    suspend fun findByPrefecture(prefecture: String): List<PostalCode>

    @Insert
    suspend fun insert(postalCode: PostalCode)
}
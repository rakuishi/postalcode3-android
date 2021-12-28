package com.rakuishi.postalcode3.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostalCodeDao {

    @Query(
        """
        SELECT * FROM postal_codes WHERE code LIKE '%' || :query || '%'
            OR prefecture LIKE '%' || :query || '%' 
            OR city LIKE '%' || :query || '%' 
            OR street LIKE '%' || :query || '%' 
            OR prefecture_pron LIKE '%' || :query || '%' 
            OR city_pron LIKE '%' || :query || '%' 
            OR street_pron LIKE '%' || :query || '%'
        """
    )
    suspend fun search(query: String): List<PostalCode>

    @Query("SELECT * FROM postal_codes WHERE prefecture LIKE :prefecture")
    suspend fun findByPrefecture(prefecture: String): List<PostalCode>

    @Insert
    suspend fun insert(postalCode: PostalCode)
}
package com.rakuishi.postalcode3.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostalCodeDao {

    @Query(
        """
        SELECT * FROM postal_codes  
        JOIN postal_codes_fts ON postal_codes.id = postal_codes_fts.rowid
        WHERE postal_codes_fts MATCH :query
    """
    )
    suspend fun search(query: String): List<PostalCode>

    @Query("SELECT * FROM postal_codes WHERE prefecture LIKE :prefecture")
    suspend fun findByPrefecture(prefecture: String): List<PostalCode>

    @Insert
    suspend fun insert(postalCode: PostalCode)
}
package com.rakuishi.postalcode3.repository

import com.rakuishi.postalcode3.database.PostalCode
import com.rakuishi.postalcode3.database.PostalCodeDao

class PostalCodeRepository(
    private val dao: PostalCodeDao
) {

    suspend fun search(query: String): List<PostalCode> {
        // FIXME: Replace findByPrefecture -> search. FTS doesn't work now!
        return dao.findByPrefecture(query)
    }
}
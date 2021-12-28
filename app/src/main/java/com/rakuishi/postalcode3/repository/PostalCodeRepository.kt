package com.rakuishi.postalcode3.repository

import com.rakuishi.postalcode3.database.PostalCode
import com.rakuishi.postalcode3.database.PostalCodeDao

class PostalCodeRepository(
    private val dao: PostalCodeDao
) {

    suspend fun search(keyword: String): List<PostalCode> {
        // 福岡県福岡市 → 福岡県 福岡市
        val queries = keyword
            .replace("-", "")
            .replace("都", "都 ")
            .replace("道", "道 ")
            .replace("府", "府 ")
            .replace("県", "県 ")
            .replace("市", "市 ")
            .replace("町", "町 ")
            .replace("村", "村 ")
            .replace("区", "区 ")
            .replace("郡", "郡 ")
            .split(" ")

        var postalCodes: List<PostalCode> = dao.search(queries.first())

        for (index in 1 until queries.size) {
            val temp = arrayListOf<PostalCode>()
            for (postalCode in postalCodes) {
                if (postalCode.contains(queries[index])) {
                    temp.add(postalCode)
                }
            }

            if (temp.size == 0) {
                break
            } else {
                postalCodes = temp
            }
        }

        return postalCodes
    }
}
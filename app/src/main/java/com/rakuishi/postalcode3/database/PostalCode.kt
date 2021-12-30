package com.rakuishi.postalcode3.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "postal_codes")
data class PostalCode(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "code") val code: String,
    @ColumnInfo(name = "prefecture") val prefecture: String,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "street") val street: String,
    @ColumnInfo(name = "prefecture_pron") val prefecturePron: String,
    @ColumnInfo(name = "city_pron") val cityPron: String,
    @ColumnInfo(name = "street_pron") val streetPron: String,
) : Serializable {

    constructor(id: Int, code: String, prefecture: String, city: String, street: String)
            : this(id, code, prefecture, city, street, "", "", "")

    override fun toString(): String {
        return "$hyphenedCode $prefecture $city $street"
    }

    val name: String
        get() = "$prefecture $city $street"

    val hyphenedCode: String
        get() = if (code.length == 7) "${code.substring(0, 3)}-${code.substring(3)}" else ""

    fun contains(query: String): Boolean {
        return prefecture.contains(query)
                || prefecturePron.contains(query)
                || city.contains(query)
                || cityPron.contains(query)
                || street.contains(query)
                || streetPron.contains(query)
    }
}
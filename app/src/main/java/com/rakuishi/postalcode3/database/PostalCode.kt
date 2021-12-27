package com.rakuishi.postalcode3.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "postal_codes")
data class PostalCode(
    @PrimaryKey @ColumnInfo(name = "code") val code: String,
    @ColumnInfo(name = "prefecture") val prefecture: String,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "street") val street: String,
    @ColumnInfo(name = "prefecture_pron") val prefecturePron: String,
    @ColumnInfo(name = "city_pron") val cityPron: String,
    @ColumnInfo(name = "street_pron") val streetPron: String,
) {

    constructor(code: String, prefecture: String, city: String, street: String)
            : this(code, prefecture, city, street, "", "", "")
}
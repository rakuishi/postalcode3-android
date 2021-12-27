package com.rakuishi.postalcode3.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PostalCode::class, PostalCodeFTS::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postalCodeDao(): PostalCodeDao
}
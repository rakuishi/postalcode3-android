package com.rakuishi.postalcode3.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [PostalCode::class],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(appContext: Application): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    appContext,
                    AppDatabase::class.java,
                    "app.db"
                )
                    .createFromAsset("app.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun postalCodeDao(): PostalCodeDao
}
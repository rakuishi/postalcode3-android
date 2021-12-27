package com.rakuishi.postalcode3

import android.app.Application
import android.util.Log
import com.rakuishi.postalcode3.database.AppDatabase
import kotlinx.coroutines.runBlocking

class App : Application() {

    lateinit var appDatabase: AppDatabase

    override fun onCreate() {
        super.onCreate()

        appDatabase = AppDatabase.getDatabase(this)

        // TODO: Remove this code later
        runBlocking {
            val postalCode = appDatabase.postalCodeDao().findByPrefecture("北海道").firstOrNull()
            Log.d("AppDatabase", postalCode?.toString() ?: "")
        }
    }
}
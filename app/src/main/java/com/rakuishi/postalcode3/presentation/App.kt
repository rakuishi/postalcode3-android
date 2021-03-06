package com.rakuishi.postalcode3.presentation

import android.app.Application
import com.google.android.material.color.DynamicColors
import com.rakuishi.postalcode3.BuildConfig
import com.rakuishi.postalcode3.database.AppDatabase
import com.rakuishi.postalcode3.repository.PostalCodeRepository
import timber.log.Timber

class App : Application() {

    lateinit var appDatabase: AppDatabase
    lateinit var postalCodeRepository: PostalCodeRepository

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // Material You
        DynamicColors.applyToActivitiesIfAvailable(this)

        appDatabase = AppDatabase.getDatabase(this)
        postalCodeRepository = PostalCodeRepository(appDatabase.postalCodeDao())
    }
}
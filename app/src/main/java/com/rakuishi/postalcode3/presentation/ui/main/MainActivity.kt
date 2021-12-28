package com.rakuishi.postalcode3.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rakuishi.postalcode3.R
import com.rakuishi.postalcode3.presentation.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchFragment.newInstance())
                .commitNow()
        }
    }
}
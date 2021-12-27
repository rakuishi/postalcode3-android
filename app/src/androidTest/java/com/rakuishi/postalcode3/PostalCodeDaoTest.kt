package com.rakuishi.postalcode3

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rakuishi.postalcode3.database.AppDatabase
import com.rakuishi.postalcode3.database.PostalCode
import com.rakuishi.postalcode3.database.PostalCodeDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class PostalCodeDaoTest {

    private lateinit var postalCodeDao: PostalCodeDao
    private lateinit var db: AppDatabase

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())

        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        postalCodeDao = db.postalCodeDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        Dispatchers.resetMain()
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertPostalCode() = runTest {
        val postalCode = PostalCode("0640941", "北海道", "札幌市中央区", "旭ケ丘")
        postalCodeDao.insert(postalCode)

        val postalCodeList1 = postalCodeDao.findByPrefecture("北海道")
        assertEquals(postalCodeList1.size, 1)

        val postalCodeList2 = postalCodeDao.findByPrefecture("東京都")
        assertEquals(postalCodeList2.size, 0)
    }
}
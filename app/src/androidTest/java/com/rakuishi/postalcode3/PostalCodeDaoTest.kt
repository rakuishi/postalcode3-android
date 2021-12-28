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
    fun tearDown() {
        Dispatchers.resetMain()
        db.close()
    }

    @Test
    fun findByPrefecture() = runTest {
        val postalCode = PostalCode(1, "0640941", "北海道", "札幌市中央区", "旭ケ丘")
        postalCodeDao.insert(postalCode)

        val postalCodeList1 = postalCodeDao.findByPrefecture("北海道")
        assertEquals(postalCodeList1.size, 1)

        val postalCodeList2 = postalCodeDao.findByPrefecture("東京都")
        assertEquals(postalCodeList2.size, 0)
    }

    @Test
    fun search() = runTest {
        postalCodeDao.insert(PostalCode(1, "0640941", "北海道", "札幌市中央区", "旭ケ丘"))
        postalCodeDao.insert(PostalCode(2, "0600041", "北海道", "札幌市中央区", "大通東"))
        postalCodeDao.insert(PostalCode(3, "0600042", "北海道", "札幌市中央区", "大通西（１〜１９丁目）"))

        val postalCodeList1 = postalCodeDao.search("北海道")
        assertEquals(postalCodeList1.size, 3)

        val postalCodeList2 = postalCodeDao.search("札幌市")
        assertEquals(postalCodeList2.size, 3)

        val postalCodeList3 = postalCodeDao.search("大通")
        assertEquals(postalCodeList3.size, 2)
    }
}
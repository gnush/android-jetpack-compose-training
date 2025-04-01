package com.example.inventory

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.inventory.data.InventoryDatabase
import com.example.inventory.data.Item
import com.example.inventory.data.ItemDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class ItemDaoTest {
    private lateinit var itemDao: ItemDao
    private lateinit var inventoryDatabase: InventoryDatabase

    @Before
    fun createDB() {
        val context: Context = ApplicationProvider.getApplicationContext()
        inventoryDatabase = Room.inMemoryDatabaseBuilder(context, InventoryDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        itemDao = inventoryDatabase.itemDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        inventoryDatabase.close()
    }

    private var item1 = Item(1, "Apple", 10.0, 77)
    private var item2 = Item(2, "Banana", 15.0, 101)
    private var item3 = Item(3, "Orange", 5.0, 9001)

    private suspend fun addOneItemToDb() {
        itemDao.insert(item1)
    }

    private suspend fun addThreeItemsToDb() {
        itemDao.insert(item1)
        itemDao.insert(item2)
        itemDao.insert(item3)
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsItemToDB() = runBlocking {
        addOneItemToDb()
        val allItems = itemDao.getAllItems().first()
        assertEquals(1, allItems.size)
        assertEquals(item1, allItems[0])
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllItems_returnsAllItemsFromDB() = runBlocking {
        addThreeItemsToDb()
        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems.size, 3)
        assertEquals(item1, allItems[0])
        assertEquals(item2, allItems[1])
        assertEquals(item3, allItems[2])
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdateItems_updatesItemsInDB() = runBlocking {
        addThreeItemsToDb()
        itemDao.update(Item(1, "Apple", 15.0, 75))
        itemDao.update(Item(2, "Banana", 10.0, 5))
        itemDao.update(Item(3, "Orange", 9.0, 5005))

        val allItems = itemDao.getAllItems().first()

        assertEquals(Item(1, "Apple", 15.0, 75), allItems[0])
        assertEquals(Item(2, "Banana", 10.0, 5), allItems[1])
        assertEquals(Item(3, "Orange", 9.0, 5005), allItems[2])
    }

    @Test
    @Throws(Exception::class)
    fun daoDeleteItems_deletesAllItemsFromDB() = runBlocking {
        addThreeItemsToDb()
        itemDao.delete(item1)
        itemDao.delete(item2)
        itemDao.delete(item3)

        val allItems = itemDao.getAllItems().first()
        assertTrue(allItems.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun daoGetItem_returnsItemFromDB() = runBlocking {
        addOneItemToDb()
        val item = itemDao.getItem(1)
        assertEquals(item.first(), item1)
    }
}
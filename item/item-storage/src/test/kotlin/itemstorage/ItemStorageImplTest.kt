package itemstorage

import commontest.runTest
import itemdata.model.ItemEntity
import itemstorage.test.factory.ItemDataFactory
import storage.StoreEngine
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull


class ItemStorageImplTest {

    private val storeEngine = object : StoreEngine<ItemEntity> {
        private var itemEntity: ItemEntity? = null
        override suspend fun save(value: ItemEntity) {
            itemEntity = value
        }

        override suspend fun get(): ItemEntity? {
            return itemEntity
        }

        override suspend fun clear() {
            itemEntity = null
        }

    }
    private val itemStorage = ItemStorageImpl(storeEngine)

    @Test
    fun testStorageSavesData() = runTest {
        itemStorage.save(ItemDataFactory.makeAnotherItemEntity())
        assertNotNull(itemStorage.get())
    }

    @Test
    fun testStorageRetrievesData() = runTest {
        itemStorage.save(ItemDataFactory.makeItemEntity())
        assertEquals(ItemDataFactory.makeItemEntity(), itemStorage.get())
    }

    @Test
    fun testStorageClearsData() = runTest {
        itemStorage.clear()
        assertNull(itemStorage.get())
    }
}
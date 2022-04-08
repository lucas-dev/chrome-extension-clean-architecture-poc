package itemdata

import commontest.runTest
import itemdata.sources.ItemHtml
import itemdata.model.ItemEntity
import itemdata.sources.ItemStorage
import itemdata.test.factory.ItemDataFactory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ItemRepositoryImplTest {

    private val storageMocked = object : ItemStorage {
        var itemEntity: ItemEntity? = null
        override suspend fun save(item: ItemEntity) {
            itemEntity = item
        }

        override suspend fun get(): ItemEntity? {
            return itemEntity
        }

        override suspend fun clear() {
            itemEntity = null
        }
    }

    private val itemEntityMocked = ItemDataFactory.makeItemEntity()

    @Test
    fun When_DocumentContainsItemId_Expect_ItemIsSavedToStorage() = runTest {
        val itemHtmlMocked = object : ItemHtml {

            override fun extract(): ItemEntity? {
                return itemEntityMocked
            }
        }


        val repository = ItemRepositoryImpl(storageMocked, itemHtmlMocked)
        repository.fetchAndStore()
        assertEquals(itemEntityMocked, storageMocked.get())
    }

    @Test
    fun When_DocumentIsMissingItemId_Expect_ItemCannotBeSavedToStorage() = runTest {
        val itemHtmlMocked = object : ItemHtml {

            override fun extract(): ItemEntity? {
                return null
            }
        }


        val repository = ItemRepositoryImpl(storageMocked, itemHtmlMocked)
        repository.fetchAndStore()
        assertNull(storageMocked.get())
    }

    @Test
    fun testItemIsRetrievedFromStorage() = runTest {
        storageMocked.save(itemEntityMocked)
        assertEquals(itemEntityMocked, storageMocked.get())
    }
}
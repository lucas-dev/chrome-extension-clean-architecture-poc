package itemdomain.interactors

import commontest.runTest
import itemdomain.interactors.test.factory.ItemDataFactory
import itemdomain.model.Item
import itemdomain.repository.ItemRepository
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class FetchAndStoreItemTest {

    @Test
    fun testItemFetchedAndStoredSuccessfully() = runTest {
        // given
        var item: Item? = null

        val repositoryMocked: ItemRepository = object : ItemRepository {
            override suspend fun fetchAndStore() {
                item = ItemDataFactory.makeItem()
            }

            override suspend fun retrieve(): Item? {
                return item
            }

        }
        val useCase = FetchAndStoreItem(repositoryMocked)

        // when
        useCase.invoke()

        // then
        assertNotNull(repositoryMocked.retrieve())
        assertEquals(repositoryMocked.retrieve()?.id ?: "", "1986")
    }

    @Test
    fun testFetchAndStoreItemFailed() = runTest {
        // given
        var item: Item? = null

        val repositoryMocked: ItemRepository = object : ItemRepository {
            override suspend fun fetchAndStore() {
                item = null
            }

            override suspend fun retrieve(): Item? {
                return item
            }

        }
        val useCase = FetchAndStoreItem(repositoryMocked)

        // when
        useCase.invoke()

        // then
        assertNull(repositoryMocked.retrieve())
    }
}
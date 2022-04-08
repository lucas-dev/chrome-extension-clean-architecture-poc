package reviewsstorage

import commontest.runTest
import reviewsstorage.mapper.ReviewStoreMapper
import reviewsstorage.model.ReviewStore
import reviewsstorage.test.factory.ReviewsDataFactory
import storage.StoreEngine
import kotlin.test.*


class ReviewsStorageImplTest {

    private val storeEngine = object : StoreEngine<List<ReviewStore>> {
        private var reviewsStore: List<ReviewStore>? = null
        override suspend fun save(value: List<ReviewStore>) {
            reviewsStore = value
        }

        override suspend fun get(): List<ReviewStore>? {
            return reviewsStore
        }

        override suspend fun clear() {
            reviewsStore = null
        }

    }

    private val mapper = ReviewStoreMapper()
    private val reviewsStorage = ReviewsStorageImpl(storeEngine, mapper)

    @Test
    fun testStorageSavesData() = runTest {
        reviewsStorage.save(listOf(
            ReviewsDataFactory.makeReviewEntity()
        ))
        with(reviewsStorage) {
            assertNotNull(get())
            assertTrue(get()!!.isNotEmpty())
        }
    }

    @Test
    fun testStorageRetrievesData() = runTest {
        val reviewEntity = ReviewsDataFactory.makeReviewEntity()
        reviewsStorage.save(listOf(reviewEntity))
        with(reviewsStorage) {
            assertNotNull(get())
            assertTrue(get()!!.isNotEmpty())
            assertEquals(get()?.first(), reviewEntity)
        }
    }

    @Test
    fun testStorageClearsData() = runTest {
        reviewsStorage.clear()
        assertNull(reviewsStorage.get())
    }
}
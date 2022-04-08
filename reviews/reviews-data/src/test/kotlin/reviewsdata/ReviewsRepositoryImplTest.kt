package reviewsdata

import commontest.runTest
import reviewsdata.mapper.ReviewEntityMapper
import reviewsdata.model.ReviewEntity
import reviewsdata.sources.ReviewsRemote
import reviewsdata.sources.ReviewsStorage
import reviewsdata.test.factory.ReviewsDataFactory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ReviewsRepositoryImplTest {
    private val reviewMocked = ReviewsDataFactory.makeReviewEntity()

    private val storageMocked = object : ReviewsStorage {
        var reviews: List<ReviewEntity>? = null
        override suspend fun save(reviews: List<ReviewEntity>) {
            this.reviews = reviews
        }

        override suspend fun get(): List<ReviewEntity>? {
            return reviews
        }

        override suspend fun clear() {
            this.reviews = null
        }

    }

    private val remote = object : ReviewsRemote {
        override suspend fun fetchReviewsBy(itemId: String): List<ReviewEntity> {
            return listOf(reviewMocked)
        }
    }

    private val mapper = ReviewEntityMapper()

    private val repository = ReviewsRepositoryImpl(storageMocked, remote, mapper)


    @Test
    fun When_ReviewsAreFetchedFromApi_Expect_DataIsStored() = runTest {
        repository.fetchAndStore("123")
        assertNotNull(repository.retrieve())
        assertEquals(1, repository.retrieve()?.size)
    }

    @Test
    fun StorageRetrievesReviewAfterDataIsFetchedFromServiceAndSaved() = runTest {
        repository.fetchAndStore("123")
        assertEquals(repository.retrieve()?.first()?.id, reviewMocked.id)
        assertEquals(repository.retrieve()?.first()?.dateCreated, reviewMocked.dateCreated)
        assertEquals(repository.retrieve()?.first()?.status, reviewMocked.status)
        assertEquals(repository.retrieve()?.first()?.title, reviewMocked.title)
        assertEquals(repository.retrieve()?.first()?.content, reviewMocked.content)
        assertEquals(repository.retrieve()?.first()?.rate, reviewMocked.rate)
        assertEquals(repository.retrieve()?.first()?.valorization, reviewMocked.valorization)
        assertEquals(repository.retrieve()?.first()?.likes, reviewMocked.likes)
        assertEquals(repository.retrieve()?.first()?.dislikes, reviewMocked.dislikes)
        assertEquals(repository.retrieve()?.first()?.reviewerId, reviewMocked.reviewerId)
        assertEquals(repository.retrieve()?.first()?.buyingDate, reviewMocked.buyingDate)
    }

    @Test
    fun testDataIsClearedFromStorage() = runTest {
        repository.fetchAndStore("123")
        repository.clear()
        assertNull(repository.retrieve())
    }
}
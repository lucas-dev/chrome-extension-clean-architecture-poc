package reviewsdomain.interactors

import commondomain.MainThread
import commondomain.Result
import commondomain.data
import commontest.runTest
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import reviewsdomain.model.Review
import reviewsdomain.repository.ReviewsRepository
import reviewsdomain.test.factory.ReviewsDataFactory
import kotlin.test.*

class GetReviewsStoredTest {
    private val reviewMocked = ReviewsDataFactory.makeReview()

    private val repository = object : ReviewsRepository {
        private var review: Review? = null
        override suspend fun fetchAndStore(itemId: String) {
            review = reviewMocked
        }

        override suspend fun retrieve(): List<Review>? {
            return listOf(review!!)
        }

        override suspend fun clear() {
            review = null
        }
    }

    private val postExecutionThread = MainThread()

    @Test
    fun Given_ReviewsNotStored_When_RetrievingStoredReviews_Then_ExceptionThrown() = runTest {
        // given
        val useCase = GetReviewsStored(repository, postExecutionThread)

        // when
        val result = useCase(Unit).drop(1).first()

        // then
        assertIs<Result.Error>(result)
    }

    @Test
    fun When_ReviewsAreAvailable_Expect_ReviewsListRetrieved() = runTest {
        // given
        repository.fetchAndStore("123")

        // when
        val useCase = GetReviewsStored(repository, postExecutionThread)
        val result = useCase(Unit).drop(1).first().data

        // then
        assertNotNull(result)
        assertEquals(result.size, 1)
    }

}
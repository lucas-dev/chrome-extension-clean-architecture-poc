package reviewsdomain.interactors

import commondomain.MainThread
import commondomain.Result
import commondomain.data
import commontest.runTest
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import reviewsdomain.helpers.StringHelperReviews
import reviewsdomain.model.Review
import reviewsdomain.repository.ReviewsRepository
import reviewsdomain.test.factory.ReviewsDataFactory
import kotlin.test.*

class GetReviewsFilteredTest {
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

    private val stringHelper = StringHelperReviews()
    private val postExecutionThread = MainThread()

    @BeforeTest
    fun setUp() = runTest {
        repository.fetchAndStore("123")
    }

    @Test
    fun Should_EmitLoadingAsFirstState_When_UseCaseIsInvoked() = runTest {
        // given
        val useCase = GetReviewsFiltered(repository, stringHelper, postExecutionThread)

        // when
        val result = useCase("query").first()

        // then
        assertIs<Result.Loading>(result)
    }

    @Test
    fun Should_ReturnReviewsFilteredBySearchTerm_IfThereAreReviewsMatchingSearchTerm() = runTest {
        // given
        val useCase = GetReviewsFiltered(repository, stringHelper, postExecutionThread)

        // when
        val result = useCase("works").drop(1).first().data?.first()

        // then
        assertNotNull(result)
        assertEquals(result, reviewMocked)
    }

    @Test
    fun Should_ReturnEmptyListOfReviews_IfFilteringDoesntFindMatches() = runTest {
        // given
        val useCase = GetReviewsFiltered(repository, stringHelper, postExecutionThread)

        // when
        val result = useCase("randon search query").drop(1).first().data

        // then
        assertEquals(result?.size, 0)
    }

}
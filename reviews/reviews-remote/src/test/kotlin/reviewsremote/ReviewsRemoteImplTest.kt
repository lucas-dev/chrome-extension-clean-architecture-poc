package reviewsremote

import commonremote.RemoteMapper
import commontest.runTest
import reviewsdata.model.ReviewEntity
import reviewsremote.api.ReviewsApi
import reviewsremote.mapper.ReviewsResponseMapper
import reviewsremote.model.ReviewsResponse
import reviewsremote.test.factory.ReviewsDataFactory
import kotlin.test.*

class ReviewsRemoteImplTest {
    private val reviewMocked = ReviewsDataFactory.makeReviewResponse()

    private val api: ReviewsApi = object : ReviewsApi {
        override suspend fun fetchReviewsBy(itemId: String): ReviewsResponse {
            return ReviewsResponse(
                paging = ReviewsResponse.Paging(10, 10, 10),
                ratingAverage = 4,
                reviews = listOf(reviewMocked)
            )
        }

    }
    private val mapper: RemoteMapper<ReviewsResponse.ReviewResponse, ReviewEntity> =
        ReviewsResponseMapper()

    @Test
    fun When_ReviewsAreFetchedFromApi_Expect_ResponseMappedToDataLayerEntity() = runTest {
        val remote = ReviewsRemoteImpl(api = api, mapper = mapper)
        val questionsFetched = remote.fetchReviewsBy("123")
        assertEquals(questionsFetched.first().id, reviewMocked.id)
        assertEquals(questionsFetched.first().dateCreated, reviewMocked.dateCreated)
        assertEquals(questionsFetched.first().status, reviewMocked.status)
        assertEquals(questionsFetched.first().title, reviewMocked.title)
        assertEquals(questionsFetched.first().content, reviewMocked.content)
        assertEquals(questionsFetched.first().rate, reviewMocked.rate)
        assertEquals(questionsFetched.first().valorization, reviewMocked.valorization)
        assertEquals(questionsFetched.first().likes, reviewMocked.likes)
        assertEquals(questionsFetched.first().dislikes, reviewMocked.dislikes)
        assertEquals(questionsFetched.first().reviewerId, reviewMocked.reviewerId)
        assertEquals(questionsFetched.first().buyingDate, reviewMocked.buyingDate)
    }

}
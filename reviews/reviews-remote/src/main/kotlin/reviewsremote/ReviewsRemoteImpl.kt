package reviewsremote

import commonremote.RemoteMapper
import reviewsremote.api.ReviewsApi
import reviewsdata.model.ReviewEntity
import reviewsdata.sources.ReviewsRemote
import reviewsremote.model.ReviewsResponse

class ReviewsRemoteImpl constructor(
    private val api: ReviewsApi,
    private val mapper: RemoteMapper<ReviewsResponse.ReviewResponse, ReviewEntity>
) : ReviewsRemote {

    override suspend fun fetchReviewsBy(itemId: String): List<ReviewEntity> {
        val reviews = api.fetchReviewsBy(itemId)
        return reviews.reviews.map(mapper::fromRemote)
    }
}
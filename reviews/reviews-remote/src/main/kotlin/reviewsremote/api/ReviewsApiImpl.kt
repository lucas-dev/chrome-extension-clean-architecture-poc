package reviewsremote.api

import reviewsremote.model.ReviewsResponse

class ReviewsApiImpl : ReviewsApi {
    override suspend fun fetchReviewsBy(itemId: String): ReviewsResponse {
        return ReviewsService.fetchReviewsBy(itemId)
    }

}
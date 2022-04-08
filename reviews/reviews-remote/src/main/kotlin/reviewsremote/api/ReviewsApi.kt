package reviewsremote.api

import reviewsremote.model.ReviewsResponse

interface ReviewsApi {
    suspend fun fetchReviewsBy(itemId: String): ReviewsResponse
}
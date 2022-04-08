package reviewsdata.sources

import reviewsdata.model.ReviewEntity

interface ReviewsRemote {
    suspend fun fetchReviewsBy(itemId: String): List<ReviewEntity>
}
package reviewsdata.sources

import reviewsdata.model.ReviewEntity

interface ReviewsStorage {
    suspend fun save(reviews: List<ReviewEntity>)
    suspend fun get(): List<ReviewEntity>?
    suspend fun clear()
}
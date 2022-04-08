package reviewsdomain.repository

import reviewsdomain.model.Review

interface ReviewsRepository {
    suspend fun fetchAndStore(itemId: String)
    suspend fun retrieve(): List<Review>?
    suspend fun clear()
}
package reviewsdata

import commondata.DataMapper
import reviewsdata.model.ReviewEntity
import reviewsdata.sources.ReviewsRemote
import reviewsdata.sources.ReviewsStorage
import reviewsdomain.model.Review
import reviewsdomain.repository.ReviewsRepository

class ReviewsRepositoryImpl constructor(
    private val storage: ReviewsStorage,
    private val remote: ReviewsRemote,
    private val mapper: DataMapper<ReviewEntity, Review>
) : ReviewsRepository {

    override suspend fun fetchAndStore(itemId: String) {
        val questions = remote.fetchReviewsBy(itemId)
        storage.save(questions)
    }

    override suspend fun retrieve(): List<Review>? {
        val reviews = storage.get()
        return reviews?.map(mapper::fromData)
    }

    override suspend fun clear() {
        storage.clear()
    }

}
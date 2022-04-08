package reviewsstorage

import StorageMapper
import reviewsdata.model.ReviewEntity
import reviewsdata.sources.ReviewsStorage
import reviewsstorage.model.ReviewStore
import storage.StoreEngine

class ReviewsStorageImpl constructor(
    private val storeEngine: StoreEngine<List<ReviewStore>>,
    private val mapper: StorageMapper<ReviewStore, ReviewEntity>
) : ReviewsStorage {
    override suspend fun save(reviews: List<ReviewEntity>) {
        storeEngine.save(reviews.map(mapper::toStorage))
    }

    override suspend fun get(): List<ReviewEntity>? {
        return storeEngine.get()?.map(mapper::fromStorage)
    }

    override suspend fun clear() {
        storeEngine.clear()
    }

    companion object {
        const val REVIEWS_KEY = "reviews"
    }
}
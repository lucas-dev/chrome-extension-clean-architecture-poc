package reviewsstorage.mapper

import StorageMapper
import reviewsdata.model.ReviewEntity
import reviewsstorage.model.ReviewStore

class ReviewStoreMapper : StorageMapper<ReviewStore, ReviewEntity> {
    override fun fromStorage(type: ReviewStore): ReviewEntity {
        return with(type) {
            ReviewEntity(
                id,
                dateCreated,
                status,
                title,
                content,
                rate,
                valorization,
                likes,
                dislikes,
                reviewerId,
                buyingDate
            )
        }
    }

    override fun toStorage(type: ReviewEntity): ReviewStore {
        return with(type) {
            ReviewStore(
                id,
                dateCreated,
                status,
                title,
                content,
                rate,
                valorization,
                likes,
                dislikes,
                reviewerId,
                buyingDate
            )
        }
    }
}
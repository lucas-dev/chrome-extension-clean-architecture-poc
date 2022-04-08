package reviewsdata.mapper

import commondata.DataMapper
import reviewsdata.model.ReviewEntity
import reviewsdomain.model.Review

class ReviewEntityMapper : DataMapper<ReviewEntity, Review> {
    override fun fromData(entity: ReviewEntity): Review {
        return with(entity) {
            Review(id, dateCreated, status, title, content, rate, valorization, likes, dislikes, reviewerId, buyingDate)
        }
    }

    override fun toData(domain: Review): ReviewEntity {
        return with(domain) {
            ReviewEntity(
                id, dateCreated, status, title, content, rate, valorization, likes, dislikes, reviewerId, buyingDate
            )
        }
    }
}
package reviewsremote.mapper

import commonremote.RemoteMapper
import reviewsdata.model.ReviewEntity
import reviewsremote.model.ReviewsResponse

class ReviewsResponseMapper: RemoteMapper<ReviewsResponse.ReviewResponse, ReviewEntity> {
    override fun fromRemote(model: ReviewsResponse.ReviewResponse): ReviewEntity {
        return with (model) {
            ReviewEntity(
                id = id,
                dateCreated = dateCreated,
                status = status,
                title = title,
                content = content,
                rate = rate,
                valorization = valorization,
                likes = likes,
                dislikes = dislikes,
                reviewerId = reviewerId,
                buyingDate = buyingDate,
            )
        }
    }

}
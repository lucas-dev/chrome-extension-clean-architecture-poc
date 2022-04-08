package reviewsremote.api

import commonremote.fetch
import reviewsremote.model.ReviewsResponse

object ReviewsService {
    private const val BASE_URL = "https://api.mercadolibre.com"

    suspend fun fetchReviewsBy(itemId: String): ReviewsResponse {
        val url = "$BASE_URL/reviews/item/$itemId"
        val response = url.fetch()
        return ReviewsResponse(paging = ReviewsResponse.Paging(
            total = response.paging.total,
            limit = response.paging.limit,
            offset = response.paging.offset
        ),
            reviews = (response.reviews as Array<dynamic>).map { review ->
                ReviewsResponse.ReviewResponse(
                    id = review.id,
                    dateCreated = review.date_created,
                    status = review.status,
                    title = review.title,
                    content = review.content,
                    rate = review.rate,
                    valorization = review.valorization,
                    likes = review.likes,
                    dislikes = review.dislikes,
                    reviewerId = review.reviewer_id,
                    buyingDate = review.buying_date
                )
            },
        ratingAverage = response.rating_average)
    }
}
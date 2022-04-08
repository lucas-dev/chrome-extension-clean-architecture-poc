package reviewsremote.model

data class ReviewsResponse(
    val paging: Paging,
    val ratingAverage: Int,
    val reviews: List<ReviewResponse>
) {
    data class Paging(
        val total: Int,
        val limit: Int,
        val offset: Int
    )
    data class ReviewResponse(
        val id: String,
        val dateCreated: String,
        val status: String,
        val title: String,
        val content: String,
        val rate: Int,
        val valorization: Int,
        val likes: Int,
        val dislikes: Int,
        val reviewerId: String,
        val buyingDate: String
    )
}
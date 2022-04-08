package reviewsui

data class ReviewUI(
    val title: String,
    val content: String,
    val dateCreated: String,
    val rate: Int,
    val likes: Int,
    val dislikes: Int
)

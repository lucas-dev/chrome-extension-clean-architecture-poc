package reviewsdomain.model

data class Review(
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
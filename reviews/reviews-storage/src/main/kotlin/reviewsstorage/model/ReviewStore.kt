package reviewsstorage.model

import kotlinx.serialization.Serializable

@Serializable
data class ReviewStore(
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

package questionsstorage.model

import kotlinx.serialization.Serializable

@Serializable
data class QuestionStore(
    val dateCreated: String,
    val itemId: String,
    val sellerId: String,
    val status: String,
    val text: String,
    val id: String,
    val answer: AnswerStore?
) {
    @Serializable
    data class AnswerStore(
        val text: String,
        val status: String,
        val dateCreated: String
    )
}

package questionsdomain.model

data class Question(
    val dateCreated: String,
    val itemId: String,
    val sellerId: String,
    val status: String,
    val text: String,
    val id: String,
    val answer: Answer?
) {
    data class Answer(
        val text: String,
        val status: String,
        val dateCreated: String
    )
}
package questionsdata.model

data class QuestionEntity(
    val dateCreated: String,
    val itemId: String,
    val sellerId: String,
    val status: String,
    val text: String,
    val id: String,
    val answer: AnswerEntity?
) {
    data class AnswerEntity(
        val text: String,
        val status: String,
        val dateCreated: String
    )
}

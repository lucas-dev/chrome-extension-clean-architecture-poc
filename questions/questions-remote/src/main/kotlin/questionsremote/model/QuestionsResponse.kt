package questionsremote.model

data class QuestionsResponse(
    val total: Int,
    val questions: List<QuestionResponse>
) {
    data class QuestionResponse(
        val dateCreated: String,
        val itemId: String,
        val sellerId: String,
        val status: String,
        val text: String,
        val id: String,
        val answer: AnswerResponse?
    )

    data class AnswerResponse(
        val text: String,
        val status: String,
        val dateCreated: String
    )
}
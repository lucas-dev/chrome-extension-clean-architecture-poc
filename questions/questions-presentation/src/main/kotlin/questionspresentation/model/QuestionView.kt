package questionspresentation.model

data class QuestionView(
    val text: String,
    val answer: AnswerView?
) {
    data class AnswerView(
        val text: String
    )
}
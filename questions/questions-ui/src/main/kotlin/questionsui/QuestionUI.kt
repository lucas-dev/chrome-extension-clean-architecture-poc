package questionsui

data class QuestionUI(
    val text: String,
    val answer: AnswerUI?
) {
    data class AnswerUI(
        val text: String
    )
}
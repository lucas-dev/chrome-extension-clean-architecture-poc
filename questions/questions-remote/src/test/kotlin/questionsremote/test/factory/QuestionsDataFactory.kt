package questionsremote.test.factory

import questionsremote.model.QuestionsResponse
import kotlin.js.Date

internal object QuestionsDataFactory {
    fun makeQuestionResponse() = QuestionsResponse.QuestionResponse(
        Date(2022, 10, 18).toDateString(),
        "theIDoftheItem",
        "theSellerId",
        "a_status",
        "THE question",
        "123",
        QuestionsResponse.AnswerResponse(
            "THE answer",
            "closed",
            Date(2022, 10, 18).toDateString()
        )
    )
}
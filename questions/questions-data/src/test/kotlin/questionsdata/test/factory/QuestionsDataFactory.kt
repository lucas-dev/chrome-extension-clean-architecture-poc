package questionsdata.test.factory

import questionsdata.model.QuestionEntity
import kotlin.js.Date

internal object QuestionsDataFactory {
    fun makeQuestionEntity() = QuestionEntity(
        Date(2022, 10, 18).toDateString(),
        "theIDoftheItem",
        "theSellerId",
        "a_status",
        "THE question",
        "123",
        QuestionEntity.AnswerEntity(
            "THE answer",
            "closed",
            Date(2022, 10, 18).toDateString()
        )
    )
}
package questionsstorage.test.factory

import questionsdata.model.QuestionEntity
import questionsstorage.model.QuestionStore
import kotlin.js.Date

internal object QuestionsDataFactory {
    fun makeQuestionStore() = QuestionStore(
        Date(2022, 10, 18).toDateString(),
        "theIDoftheItem",
        "theSellerId",
        "a_status",
        "THE question",
        "123",
        QuestionStore.AnswerStore(
            "THE answer",
            "closed",
            Date(2022, 10, 18).toDateString()
        )
    )
    fun makeQuestionEntity() = QuestionEntity(Date(2022, 10, 18).toDateString(),
        "theIDoftheItem",
        "theSellerId",
        "a_status",
        "THE question",
        "123",
        QuestionEntity.AnswerEntity(
            "THE answer",
            "closed",
            Date(2022, 10, 18).toDateString()
        ))
}
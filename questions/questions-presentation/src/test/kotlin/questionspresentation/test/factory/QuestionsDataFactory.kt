package questionspresentation.test.factory

import questionsdomain.model.Question
import kotlin.js.Date

internal object QuestionsDataFactory {
    fun makeQuestion() = Question(
        Date(2022, 10, 18).toDateString(),
        "theIDoftheItem",
        "theSellerId",
        "a_status",
        "the QUESTION",
        "123",
        Question.Answer(
            "THE answer",
            "closed",
            Date(2022, 10, 18).toDateString()
        )
    )
}
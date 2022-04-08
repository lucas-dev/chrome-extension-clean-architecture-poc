package questionsremote.api

import commonremote.fetch
import questionsremote.model.QuestionsResponse

object QuestionsService {
    private const val BASE_URL = "https://api.mercadolibre.com"

    suspend fun fetchQuestionsBy(itemId: String): QuestionsResponse {
        val url = "$BASE_URL/questions/search?item=$itemId"
        val response = url.fetch()
        return QuestionsResponse(total = response.total,
            questions = (response.questions as Array<dynamic>).map { question ->
                QuestionsResponse.QuestionResponse(
                    dateCreated = question.date_created,
                    itemId = question.item_id,
                    sellerId = question.seller_id,
                    status = question.status,
                    text = question.text,
                    id = question.id,
                    answer = if (question.answer != null) {
                        QuestionsResponse.AnswerResponse(
                            text = question.answer.text,
                            status = question.answer.status,
                            dateCreated = question.answer.date_created
                        )
                    } else {
                        null
                    }
                )
            })
    }
}
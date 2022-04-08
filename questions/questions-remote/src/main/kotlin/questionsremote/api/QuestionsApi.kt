package questionsremote.api

import questionsremote.model.QuestionsResponse

interface QuestionsApi {
    suspend fun fetchQuestionsBy(itemId: String): QuestionsResponse
}
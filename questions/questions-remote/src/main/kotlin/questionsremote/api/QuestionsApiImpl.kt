package questionsremote.api

import questionsremote.model.QuestionsResponse

class QuestionsApiImpl : QuestionsApi {
    override suspend fun fetchQuestionsBy(itemId: String): QuestionsResponse {
        return QuestionsService.fetchQuestionsBy(itemId)
    }

}
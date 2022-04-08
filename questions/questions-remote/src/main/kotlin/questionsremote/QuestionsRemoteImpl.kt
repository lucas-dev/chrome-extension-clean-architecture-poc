package questionsremote

import commonremote.RemoteMapper
import questionsremote.api.QuestionsApi
import questionsdata.model.QuestionEntity
import questionsdata.sources.QuestionsRemote
import questionsremote.model.QuestionsResponse

class QuestionsRemoteImpl constructor(
    private val api: QuestionsApi,
    private val mapper: RemoteMapper<QuestionsResponse.QuestionResponse, QuestionEntity>
) : QuestionsRemote {

    override suspend fun fetchQuestionsBy(itemId: String): List<QuestionEntity> {
        val questions = api.fetchQuestionsBy(itemId)
        return questions.questions.map(mapper::fromRemote)
    }


}
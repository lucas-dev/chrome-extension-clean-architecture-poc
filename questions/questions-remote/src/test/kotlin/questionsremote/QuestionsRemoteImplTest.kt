package questionsremote

import commonremote.RemoteMapper
import commontest.runTest
import questionsdata.model.QuestionEntity
import questionsremote.api.QuestionsApi
import questionsremote.mapper.QuestionsResponseMapper
import questionsremote.model.QuestionsResponse
import questionsremote.test.factory.QuestionsDataFactory
import kotlin.test.*

class QuestionsRemoteImplTest {
    private val questionMocked = QuestionsDataFactory.makeQuestionResponse()

    private val api: QuestionsApi = object : QuestionsApi {
        override suspend fun fetchQuestionsBy(itemId: String): QuestionsResponse {
            return QuestionsResponse(total = 1, questions = listOf(questionMocked))
        }

    }
    private val mapper: RemoteMapper<QuestionsResponse.QuestionResponse, QuestionEntity> =
        QuestionsResponseMapper()

    @Test
    fun When_QuestionsAreFetchedFromApi_Expect_ResponseMappedToDataLayerEntity() = runTest {
        val remote = QuestionsRemoteImpl(api = api, mapper = mapper)
        val questionsFetched = remote.fetchQuestionsBy("123")
        assertEquals(questionsFetched.first().id, questionMocked.id)
        assertEquals(questionsFetched.first().itemId, questionMocked.itemId)
        assertEquals(questionsFetched.first().status, questionMocked.status)
        assertEquals(questionsFetched.first().dateCreated, questionMocked.dateCreated)
        assertEquals(questionsFetched.first().text, questionMocked.text)
        assertEquals(questionsFetched.first().answer?.text, questionMocked.answer?.text)
        assertEquals(questionsFetched.first().answer?.status, questionMocked.answer?.status)
    }

}
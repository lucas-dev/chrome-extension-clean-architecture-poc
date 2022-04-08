package questionsdomain.interactors

import commondomain.MainThread
import commondomain.Result
import commondomain.data
import commontest.runTest
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import questionsdomain.model.Question
import questionsdomain.repository.QuestionsRepository
import questionsdomain.test.factory.QuestionsDataFactory
import kotlin.test.*

class GetQuestionsStoredTest {
    private val questionMocked = QuestionsDataFactory.makeAnotherQuestion()

    private val repository = object : QuestionsRepository {
        private var question: Question? = null
        override suspend fun fetchAndStore(itemId: String) {
            question = questionMocked
        }

        override suspend fun retrieve(): List<Question>? {
            return listOf(question!!)
        }

        override suspend fun clear() {
            question = null
        }
    }

    private val postExecutionThread = MainThread()

    @Test
    fun Given_QuestionsNotStored_When_RetrievingStoredQuestions_Then_ExceptionThrown() = runTest {
        // given
        val useCase = GetQuestionsStored(repository, postExecutionThread)

        // when
        val result = useCase(Unit).drop(1).first()

        // then
        assertIs<Result.Error>(result)
    }

    @Test
    fun When_QuestionsAreAvailable_Expect_QuestionsListRetrieved() = runTest {
        // given
        repository.fetchAndStore("123")

        // when
        val useCase = GetQuestionsStored(repository, postExecutionThread)
        val result = useCase(Unit).drop(1).first().data

        // then
        assertNotNull(result)
        assertEquals(result.size, 1)
    }

}
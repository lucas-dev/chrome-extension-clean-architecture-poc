package questionsdata

import commontest.runTest
import questionsdata.mapper.QuestionEntityMapper
import questionsdata.model.QuestionEntity
import questionsdata.sources.QuestionsRemote
import questionsdata.sources.QuestionsStorage
import questionsdata.test.factory.QuestionsDataFactory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class QuestionsRepositoryImplTest {
    private val questionMocked = QuestionsDataFactory.makeQuestionEntity()

    private val storageMocked = object : QuestionsStorage {
        var questions: List<QuestionEntity>? = null
        override suspend fun save(questions: List<QuestionEntity>) {
            this.questions = questions
        }

        override suspend fun get(): List<QuestionEntity>? {
            return questions
        }

        override suspend fun clear() {
            this.questions = null
        }

    }

    private val remote = object : QuestionsRemote {
        override suspend fun fetchQuestionsBy(itemId: String): List<QuestionEntity> {
            return listOf(questionMocked)
        }
    }

    private val mapper = QuestionEntityMapper()

    private val repository = QuestionsRepositoryImpl(storageMocked, remote, mapper)


    @Test
    fun When_QuestionsAreFetchedFromApi_Expect_DataIsStored() = runTest {
        repository.fetchAndStore("123")
        assertNotNull(repository.retrieve())
        assertEquals(1, repository.retrieve()?.size)
    }

    @Test
    fun StorageRetrievesQuestionAfterDataIsFetchedFromServiceAndSaved() = runTest {
        repository.fetchAndStore("123")
        assertEquals(repository.retrieve()?.first()?.id, questionMocked.id)
        assertEquals(repository.retrieve()?.first()?.itemId, questionMocked.itemId)
        assertEquals(repository.retrieve()?.first()?.status, questionMocked.status)
        assertEquals(repository.retrieve()?.first()?.dateCreated, questionMocked.dateCreated)
        assertEquals(repository.retrieve()?.first()?.text, questionMocked.text)
        assertEquals(repository.retrieve()?.first()?.answer?.text, questionMocked.answer?.text)
        assertEquals(repository.retrieve()?.first()?.answer?.status, questionMocked.answer?.status)
    }

    @Test
    fun testDataIsClearedFromStorage() = runTest {
        repository.fetchAndStore("123")
        repository.clear()
        assertNull(repository.retrieve())
    }
}
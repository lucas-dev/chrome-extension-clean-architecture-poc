package questionsstorage

import commontest.runTest
import questionsstorage.mapper.QuestionStoreMapper
import questionsstorage.model.QuestionStore
import questionsstorage.test.factory.QuestionsDataFactory
import storage.StoreEngine
import kotlin.test.*


class QuestionsStorageImplTest {

    private val storeEngine = object : StoreEngine<List<QuestionStore>> {
        private var questionStore: List<QuestionStore>? = null
        override suspend fun save(value: List<QuestionStore>) {
            questionStore = value
        }

        override suspend fun get(): List<QuestionStore>? {
            return questionStore
        }

        override suspend fun clear() {
            questionStore = null
        }

    }

    private val mapper = QuestionStoreMapper()
    private val questionsStorage = QuestionsStorageImpl(storeEngine, mapper)

    @Test
    fun testStorageSavesData() = runTest {
        questionsStorage.save(listOf(QuestionsDataFactory.makeQuestionEntity()))
        with(questionsStorage) {
            assertNotNull(get())
            assertTrue(get()!!.isNotEmpty())
        }
    }

    @Test
    fun testStorageRetrievesData() = runTest {
        val questionEntity = QuestionsDataFactory.makeQuestionEntity()
        questionsStorage.save(listOf(questionEntity))
        with(questionsStorage) {
            assertNotNull(get())
            assertTrue(get()!!.isNotEmpty())
            assertEquals(get()?.first(), questionEntity)
        }
    }

    @Test
    fun testStorageClearsData() = runTest {
        questionsStorage.clear()
        assertNull(questionsStorage.get())
    }
}
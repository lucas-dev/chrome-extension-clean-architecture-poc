package questionsstorage

import StorageMapper
import questionsdata.model.QuestionEntity
import questionsdata.sources.QuestionsStorage
import questionsstorage.model.QuestionStore
import storage.StoreEngine

class QuestionsStorageImpl constructor(
    private val storeEngine: StoreEngine<List<QuestionStore>>,
    private val mapper: StorageMapper<QuestionStore, QuestionEntity>
) : QuestionsStorage {
    override suspend fun save(questions: List<QuestionEntity>) {
        storeEngine.save(questions.map(
            mapper::toStorage
        ))
    }

    override suspend fun get(): List<QuestionEntity>? {
        return storeEngine.get()?.map(mapper::fromStorage)
    }

    override suspend fun clear() {
        storeEngine.clear()
    }

    companion object {
        const val QUESTIONS_KEY = "questions"
    }
}
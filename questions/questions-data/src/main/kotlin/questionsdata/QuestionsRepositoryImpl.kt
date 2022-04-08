package questionsdata

import commondata.DataMapper
import questionsdata.model.QuestionEntity
import questionsdata.sources.QuestionsRemote
import questionsdata.sources.QuestionsStorage
import questionsdomain.model.Question
import questionsdomain.repository.QuestionsRepository

class QuestionsRepositoryImpl constructor(
    private val storage: QuestionsStorage,
    private val remote: QuestionsRemote,
    private val mapper: DataMapper<QuestionEntity, Question>
) : QuestionsRepository {

    override suspend fun fetchAndStore(itemId: String) {
        val questions = remote.fetchQuestionsBy(itemId)
        storage.save(questions)
    }

    override suspend fun retrieve(): List<Question>? {
        val questions = storage.get()
        return questions?.map(mapper::fromData)
    }

    override suspend fun clear() {
        storage.clear()
    }
}
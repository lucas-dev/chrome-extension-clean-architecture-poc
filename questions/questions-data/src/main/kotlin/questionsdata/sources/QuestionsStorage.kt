package questionsdata.sources

import questionsdata.model.QuestionEntity

interface QuestionsStorage {
    suspend fun save(questions: List<QuestionEntity>)
    suspend fun get(): List<QuestionEntity>?
    suspend fun clear()
}
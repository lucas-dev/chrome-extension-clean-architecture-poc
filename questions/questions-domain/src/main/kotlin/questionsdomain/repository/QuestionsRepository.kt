package questionsdomain.repository

import questionsdomain.model.Question

interface QuestionsRepository {
    suspend fun fetchAndStore(itemId: String)
    suspend fun retrieve(): List<Question>?
    suspend fun clear()
}
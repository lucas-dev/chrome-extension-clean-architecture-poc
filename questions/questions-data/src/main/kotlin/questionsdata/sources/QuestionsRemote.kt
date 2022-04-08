package questionsdata.sources

import questionsdata.model.QuestionEntity

interface QuestionsRemote {
    suspend fun fetchQuestionsBy(itemId: String): List<QuestionEntity>
}
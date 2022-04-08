package questionsdomain.interactors

import commondomain.MainThread
import commondomain.Result
import commondomain.data
import commontest.runTest
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import questionsdomain.helpers.StringHelperQuestions
import questionsdomain.model.Question
import questionsdomain.repository.QuestionsRepository
import questionsdomain.test.factory.QuestionsDataFactory
import kotlin.test.*

class GetQuestionsFilteredTest {
    private val questionMocked = QuestionsDataFactory.makeQuestion()

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

    private val stringHelper = StringHelperQuestions()
    private val postExecutionThread = MainThread()

    @BeforeTest
    fun setUp() = runTest {
        repository.fetchAndStore("123")
    }

    @Test
    fun Should_EmitLoadingAsFirstState_When_UseCaseIsInvoked() = runTest {
        // given
        val useCase = GetQuestionsFiltered(repository, stringHelper, postExecutionThread)

        // when
        val result = useCase("query").first()

        // then
        assertIs<Result.Loading>(result)
    }

    @Test
    fun Should_ReturnQuestionsFilteredBySearchTerm_IfThereAreQuestionsMatchingSearchTerm() = runTest {
        // given
        val useCase = GetQuestionsFiltered(repository, stringHelper, postExecutionThread)

        // when
        val result = useCase("crysis").drop(1).first().data?.first()

        // then
        assertNotNull(result)
        assertEquals(result, questionMocked)
    }

    @Test
    fun Should_ReturnEmptyListOfQuestions_IfFilteringDoesntFindMatches() = runTest {
        // given
        val useCase = GetQuestionsFiltered(repository, stringHelper, postExecutionThread)

        // when
        val result = useCase("randon search query").drop(1).first().data

        // then
        assertEquals(result?.size, 0)
    }

}
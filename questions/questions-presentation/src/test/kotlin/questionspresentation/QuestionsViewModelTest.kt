package questionspresentation

import commondomain.FlowUseCase
import commondomain.MainThread
import commondomain.Result
import commonpresentation.Events
import commonpresentation.LoadType
import commonpresentation.ViewStatus
import commontest.runTest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import questionsdomain.model.Question
import questionspresentation.mapper.QuestionViewMapper
import questionspresentation.test.factory.QuestionsDataFactory
import kotlin.test.*

class QuestionsViewModelTest {
    private val questionMocked = QuestionsDataFactory.makeQuestion()


    private val postExecutionThread = MainThread()
    private val mapper = QuestionViewMapper()

    @Test
    fun testLoadingStateForSearchEvent() = runTest {
        // given
        val viewModel = QuestionsViewModel(
            storedQuestionsUseCase = object : FlowUseCase<Unit, List<Question>?>(postExecutionThread) {
                override fun execute(parameters: Unit): Flow<Result<List<Question>?>> {
                    TODO("Not yet implemented")
                }
            },
            filteredQuestions = object : FlowUseCase<String, List<Question>?>(postExecutionThread) {
                override fun execute(parameters: String): Flow<Result<List<Question>?>> {
                    return flow {
                        emit(Result.Loading)
                    }
                }
            }, mapper
        )

        // when
        viewModel.dispatch(Events.Search(""))
        val state = viewModel.state.first().viewStatus

        // then
        assertIs<ViewStatus.Loading>(state)
        assertEquals(state.loadType, LoadType.SEARCH)
    }

    @Test
    fun Given_SearchEventIsDispatched_When_ThereAreQuestionsMatchingSearchCriteria_QuestionListIsReturned() = runTest {
        // given
        val viewModel = QuestionsViewModel(
            storedQuestionsUseCase = object : FlowUseCase<Unit, List<Question>?>(postExecutionThread) {
                override fun execute(parameters: Unit): Flow<Result<List<Question>?>> {
                    TODO("Not yet implemented")
                }
            },
            filteredQuestions = object : FlowUseCase<String, List<Question>?>(postExecutionThread) {
                override fun execute(parameters: String): Flow<Result<List<Question>?>> {
                    return flow {
                        emit(Result.Success(listOf(questionMocked)))
                    }
                }
            }, mapper
        )

        // when
        viewModel.dispatch(Events.Search(""))
        val state = viewModel.state.first().viewStatus

        // then
        with((state as ViewStatus.Loaded).items) {
            assertTrue(isNotEmpty())
            assertEquals(size, 1)
        }
    }

    @Test
    fun Given_SearchEventIsDispatched_When_ThereAreNoQuestionsMatchingSearchCriteria_EmptyListIsReturned() = runTest {
        // given
        val viewModel = QuestionsViewModel(
            storedQuestionsUseCase = object : FlowUseCase<Unit, List<Question>?>(postExecutionThread) {
                override fun execute(parameters: Unit): Flow<Result<List<Question>?>> {
                    TODO("Not yet implemented")
                }
            },
            filteredQuestions = object : FlowUseCase<String, List<Question>?>(postExecutionThread) {
                override fun execute(parameters: String): Flow<Result<List<Question>?>> {
                    return flow {
                        emit(Result.Success(emptyList()))
                    }
                }
            }, mapper
        )

        // when
        viewModel.dispatch(Events.Search(""))
        val state = viewModel.state.first().viewStatus

        // then
        assertTrue((state as ViewStatus.Loaded).items.isEmpty())
    }

    @Test
    fun Given_SearchEventIsDispatched_When_FetchingFilteredQuestionsFails_ErrorStateIsReturned() = runTest {
        // given
        val viewModel = QuestionsViewModel(
            storedQuestionsUseCase = object : FlowUseCase<Unit, List<Question>?>(postExecutionThread) {
                override fun execute(parameters: Unit): Flow<Result<List<Question>?>> {
                    TODO("Not yet implemented")
                }
            },
            filteredQuestions = object : FlowUseCase<String, List<Question>?>(postExecutionThread) {
                override fun execute(parameters: String): Flow<Result<List<Question>?>> {
                    return flow {
                        emit(Result.Error(Exception()))
                    }
                }
            }, mapper
        )

        // when
        viewModel.dispatch(Events.Search(""))
        val state = viewModel.state.first().viewStatus

        // then
        assertIs<ViewStatus.Error>(state)
    }

    @Test
    fun testLoadingStateForFirstLoadEvent() = runTest {
        // given
        val viewModel = QuestionsViewModel(
            storedQuestionsUseCase = object : FlowUseCase<Unit, List<Question>?>(postExecutionThread) {
                override fun execute(parameters: Unit): Flow<Result<List<Question>?>> {
                    return flow {
                        emit(Result.Loading)
                    }
                }
            },
            filteredQuestions = object : FlowUseCase<String, List<Question>?>(postExecutionThread) {
                override fun execute(parameters: String): Flow<Result<List<Question>?>> {
                    TODO("Not yet implemented")
                }
            }, mapper
        )

        // when
        viewModel.dispatch(Events.RequestList)
        val state = viewModel.state.first().viewStatus

        // then
        assertIs<ViewStatus.Loading>(state)
        assertEquals(state.loadType, LoadType.FIRST_LOAD)
    }

    @Test
    fun Given_FirstLoadEventIsDispatched_When_ThereAreQuestionsStored_QuestionListIsReturned() = runTest {
        // given
        val viewModel = QuestionsViewModel(
            storedQuestionsUseCase = object : FlowUseCase<Unit, List<Question>?>(postExecutionThread) {
                override fun execute(parameters: Unit): Flow<Result<List<Question>?>> {
                    return flow {
                        emit(Result.Success(listOf(questionMocked)))
                    }
                }
            },
            filteredQuestions = object : FlowUseCase<String, List<Question>?>(postExecutionThread) {
                override fun execute(parameters: String): Flow<Result<List<Question>?>> {
                    TODO("Not yet implemented")
                }
            }, mapper
        )

        // when
        viewModel.dispatch(Events.RequestList)
        val state = viewModel.state.first().viewStatus

        // then
        with((state as ViewStatus.Loaded).items) {
            assertTrue(isNotEmpty())
            assertEquals(size, 1)
        }
    }

    @Test
    fun Given_FirstLoadEventIsDispatched_When_ThereAreNoQuestionsStored_EmptyListIsReturned() = runTest {
        // given
        val viewModel = QuestionsViewModel(
            storedQuestionsUseCase = object : FlowUseCase<Unit, List<Question>?>(postExecutionThread) {
                override fun execute(parameters: Unit): Flow<Result<List<Question>?>> {
                    return flow {
                        emit(Result.Success(emptyList()))
                    }
                }
            },
            filteredQuestions = object : FlowUseCase<String, List<Question>?>(postExecutionThread) {
                override fun execute(parameters: String): Flow<Result<List<Question>?>> {
                    TODO("Not yet implemented")
                }
            }, mapper
        )

        // when
        viewModel.dispatch(Events.RequestList)
        val state = viewModel.state.first().viewStatus

        // then
        assertTrue((state as ViewStatus.Loaded).items.isEmpty())
    }

    @Test
    fun Given_FirstLoadEventIsDispatched_When_FetchingQuestionsStoredFails_ErrorStateIsReturned() = runTest {
        // given
        val viewModel = QuestionsViewModel(
            storedQuestionsUseCase = object : FlowUseCase<Unit, List<Question>?>(postExecutionThread) {
                override fun execute(parameters: Unit): Flow<Result<List<Question>?>> {
                    return flow {
                        emit(Result.Error(Exception()))
                    }
                }
            },
            filteredQuestions = object : FlowUseCase<String, List<Question>?>(postExecutionThread) {
                override fun execute(parameters: String): Flow<Result<List<Question>?>> {
                    TODO("Not yet implemented")
                }
            }, mapper
        )

        // when
        viewModel.dispatch(Events.RequestList)
        val state = viewModel.state.first().viewStatus

        // then
        assertIs<ViewStatus.Error>(state)
    }
}
package reviewspresentation

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
import reviewsdomain.model.Review
import reviewspresentation.mapper.ReviewViewMapper
import reviewspresentation.test.factory.ReviewsDataFactory
import kotlin.test.*

class ReviewsViewModelTest {
    private val reviewMocked = ReviewsDataFactory.makeReview()


    private val postExecutionThread = MainThread()
    private val mapper = ReviewViewMapper()

    @Test
    fun testLoadingStateForSearchEvent() = runTest {
        // given
        val viewModel = ReviewsViewModel(
            storedReviewsUseCase = object : FlowUseCase<Unit, List<Review>?>(postExecutionThread) {
                override fun execute(parameters: Unit): Flow<Result<List<Review>?>> {
                    TODO("Not yet implemented")
                }
            },
            filteredReviews = object : FlowUseCase<String, List<Review>?>(postExecutionThread) {
                override fun execute(parameters: String): Flow<Result<List<Review>?>> {
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
    fun Given_SearchEventIsDispatched_When_ThereAreReviewsMatchingSearchCriteria_ReviewListIsReturned() = runTest {
        // given
        val viewModel = ReviewsViewModel(
            storedReviewsUseCase = object : FlowUseCase<Unit, List<Review>?>(postExecutionThread) {
                override fun execute(parameters: Unit): Flow<Result<List<Review>?>> {
                    TODO("Not yet implemented")
                }
            },
            filteredReviews = object : FlowUseCase<String, List<Review>?>(postExecutionThread) {
                override fun execute(parameters: String): Flow<Result<List<Review>?>> {
                    return flow {
                        emit(Result.Success(listOf(reviewMocked)))
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
    fun Given_SearchEventIsDispatched_When_ThereAreNoReviewsMatchingSearchCriteria_EmptyListIsReturned() = runTest {
        // given
        val viewModel = ReviewsViewModel(
            storedReviewsUseCase = object : FlowUseCase<Unit, List<Review>?>(postExecutionThread) {
                override fun execute(parameters: Unit): Flow<Result<List<Review>?>> {
                    TODO("Not yet implemented")
                }
            },
            filteredReviews = object : FlowUseCase<String, List<Review>?>(postExecutionThread) {
                override fun execute(parameters: String): Flow<Result<List<Review>?>> {
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
    fun Given_SearchEventIsDispatched_When_FetchingFilteredReviewsFails_ErrorStateIsReturned() = runTest {
        // given
        val viewModel = ReviewsViewModel(
            storedReviewsUseCase = object : FlowUseCase<Unit, List<Review>?>(postExecutionThread) {
                override fun execute(parameters: Unit): Flow<Result<List<Review>?>> {
                    TODO("Not yet implemented")
                }
            },
            filteredReviews = object : FlowUseCase<String, List<Review>?>(postExecutionThread) {
                override fun execute(parameters: String): Flow<Result<List<Review>?>> {
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
        val viewModel = ReviewsViewModel(
            storedReviewsUseCase = object : FlowUseCase<Unit, List<Review>?>(postExecutionThread) {
                override fun execute(parameters: Unit): Flow<Result<List<Review>?>> {
                    return flow {
                        emit(Result.Loading)
                    }
                }
            },
            filteredReviews = object : FlowUseCase<String, List<Review>?>(postExecutionThread) {
                override fun execute(parameters: String): Flow<Result<List<Review>?>> {
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
    fun Given_FirstLoadEventIsDispatched_When_ThereAreReviewsStored_ReviewListIsReturned() = runTest {
        // given
        val viewModel = ReviewsViewModel(
            storedReviewsUseCase = object : FlowUseCase<Unit, List<Review>?>(postExecutionThread) {
                override fun execute(parameters: Unit): Flow<Result<List<Review>?>> {
                    return flow {
                        emit(Result.Success(listOf(reviewMocked)))
                    }
                }
            },
            filteredReviews = object : FlowUseCase<String, List<Review>?>(postExecutionThread) {
                override fun execute(parameters: String): Flow<Result<List<Review>?>> {
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
    fun Given_FirstLoadEventIsDispatched_When_ThereAreNoReviewssStored_EmptyListIsReturned() = runTest {
        // given
        val viewModel = ReviewsViewModel(
            storedReviewsUseCase = object : FlowUseCase<Unit, List<Review>?>(postExecutionThread) {
                override fun execute(parameters: Unit): Flow<Result<List<Review>?>> {
                    return flow {
                        emit(Result.Success(emptyList()))
                    }
                }
            },
            filteredReviews = object : FlowUseCase<String, List<Review>?>(postExecutionThread) {
                override fun execute(parameters: String): Flow<Result<List<Review>?>> {
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
    fun Given_FirstLoadEventIsDispatched_When_FetchingReviewssStoredFails_ErrorStateIsReturned() = runTest {
        // given
        val viewModel = ReviewsViewModel(
            storedReviewsUseCase = object : FlowUseCase<Unit, List<Review>?>(postExecutionThread) {
                override fun execute(parameters: Unit): Flow<Result<List<Review>?>> {
                    return flow {
                        emit(Result.Error(Exception()))
                    }
                }
            },
            filteredReviews = object : FlowUseCase<String, List<Review>?>(postExecutionThread) {
                override fun execute(parameters: String): Flow<Result<List<Review>?>> {
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
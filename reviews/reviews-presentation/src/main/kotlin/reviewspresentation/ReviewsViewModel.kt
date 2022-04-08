package reviewspresentation

import commondomain.FlowUseCase
import commondomain.Result
import commonpresentation.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import reviewsdomain.model.Review
import reviewspresentation.model.ReviewView

class ReviewsViewModel constructor(
    private val storedReviewsUseCase: FlowUseCase<Unit, List<Review>?>,
    private val filteredReviews: FlowUseCase<String, List<Review>?>,
    private val reviewMapper: PresentationMapper<ReviewView, Review>
) : ViewModel<ReviewView> {
    private val _state = MutableStateFlow(State<ReviewView>())
    override val state = _state.asStateFlow()

    override suspend fun dispatch(event: Events) {
        when(event) {
            is Events.Search -> {
                filteredReviews(event.query).collectLatest {
                    when(it) {
                        Result.Loading -> {
                            _state.emit(state.value.copy(viewStatus = ViewStatus.Loading(LoadType.SEARCH)))
                        }
                        is Result.Success -> {
                            _state.emit(
                                state.value.copy(
                                    viewStatus = ViewStatus.Loaded(
                                        items = it.data?.map(reviewMapper::toPresentation) ?: emptyList(),
                                        loadType = LoadType.SEARCH
                                    )
                                )
                            )
                        }
                        is Result.Error -> {
                            _state.emit(
                                state.value.copy(
                                    viewStatus = ViewStatus.Error("error while searching"),
                                )
                            )
                        }
                    }

                }
            }
            Events.RequestList -> {
                storedReviewsUseCase(Unit).collectLatest {
                    when (it) {
                        Result.Loading -> {
                            _state.emit(
                                state.value.copy(
                                    viewStatus = ViewStatus.Loading(LoadType.FIRST_LOAD)
                                )
                            )
                        }
                        is Result.Success -> {
                            _state.emit(
                                state.value.copy(
                                    viewStatus = ViewStatus.Loaded(
                                        items = it.data?.map(reviewMapper::toPresentation) ?: emptyList(),
                                        loadType = LoadType.FIRST_LOAD
                                    )
                                )
                            )
                        }
                        is Result.Error -> {
                            _state.emit(
                                state.value.copy(
                                    viewStatus = ViewStatus.Error("error while requesting"),
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    override suspend fun emitLoading() {
        _state.emit(
            state.value.copy(
                viewStatus = ViewStatus.Loading(LoadType.FIRST_LOAD),
            )
        )
    }
}
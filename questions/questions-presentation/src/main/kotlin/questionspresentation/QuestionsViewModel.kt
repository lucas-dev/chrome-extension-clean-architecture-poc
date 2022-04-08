package questionspresentation

import commondomain.FlowUseCase
import commondomain.Result
import commonpresentation.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import questionsdomain.model.Question
import questionspresentation.model.QuestionView

class QuestionsViewModel constructor(
    private val storedQuestionsUseCase: FlowUseCase<Unit, List<Question>?>,
    private val filteredQuestions: FlowUseCase<String, List<Question>?>,
    private val questionMapper: PresentationMapper<QuestionView, Question>,
) : ViewModel<QuestionView> {
    private val _state = MutableStateFlow(State<QuestionView>())
    override val state = _state.asStateFlow()

    override suspend fun dispatch(event: Events) {
        when (event) {
            is Events.Search -> {
                filteredQuestions(event.query).collectLatest {
                    when(it) {
                        Result.Loading -> {
                            _state.emit(state.value.copy(viewStatus = ViewStatus.Loading(LoadType.SEARCH)))
                        }
                        is Result.Success -> {
                            _state.emit(
                                state.value.copy(
                                    viewStatus = ViewStatus.Loaded(
                                        items = it.data?.map(questionMapper::toPresentation) ?: emptyList(),
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
                storedQuestionsUseCase(Unit).collectLatest {
                    when (it) {
                        Result.Loading -> {
                            _state.emit(
                                state.value.copy(
                                    viewStatus = ViewStatus.Loading(LoadType.FIRST_LOAD),
                                )
                            )
                        }
                        is Result.Success -> {
                            _state.emit(
                                state.value.copy(
                                    viewStatus = ViewStatus.Loaded(
                                        items = it.data?.map(questionMapper::toPresentation) ?: emptyList(),
                                        loadType = LoadType.FIRST_LOAD
                                    ),
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
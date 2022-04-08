package popup.mvi

import commonflow.FlowManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ViewModel constructor(
    private val flowManager: FlowManager
) {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        flowManager.listenQuestionsAndReviewsWereRetrieved {
            _state.emit(
                state.value.copy(
                    messagePassingStatus = MessagePassingStatus.DataRetrieved
                )
            )
            delay(500L)
            _state.emit(
                state.value.copy(
                    messagePassingStatus = MessagePassingStatus.Idle
                )
            )
        }
    }

    suspend fun dispatch(events: Events) {
        when (events) {
            Events.RequestItem -> {
                flowManager.requestToFetchItem()
                _state.emit(
                    state.value.copy(
                        questionsAndReviewsStatus = QuestionsAndReviewsStatus.EmitLoading
                    )
                )
            }
            Events.RequestQuestionsAndReviews -> {
                _state.emit(
                    state.value.copy(
                        questionsAndReviewsStatus = QuestionsAndReviewsStatus.RequestList
                    )
                )
            }
        }
    }
}
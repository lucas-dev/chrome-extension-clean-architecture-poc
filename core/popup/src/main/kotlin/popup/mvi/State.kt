package popup.mvi

data class State(
    val messagePassingStatus: MessagePassingStatus = MessagePassingStatus.Idle,
    val questionsAndReviewsStatus: QuestionsAndReviewsStatus = QuestionsAndReviewsStatus.Idle
)

sealed class MessagePassingStatus {
    object Idle : MessagePassingStatus()
    object DataRetrieved : MessagePassingStatus()
}

sealed class QuestionsAndReviewsStatus {
    object Idle: QuestionsAndReviewsStatus()
    object EmitLoading: QuestionsAndReviewsStatus()
    object RequestList: QuestionsAndReviewsStatus()
}
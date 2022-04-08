package popup.mvi

sealed class Events {
    object RequestItem: Events()
    object RequestQuestionsAndReviews: Events()
}

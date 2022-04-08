package commonflow

interface FlowManager {
    suspend fun requestToFetchItem()
    fun listenRequestToFetchItem(callback: suspend () -> Unit)
    suspend fun notifyItemWasFetchedAndStored()
    fun listenItemWasFetchedAndStored(callback: suspend () -> Unit)
    suspend fun notifyQuestionsAndReviewsWereRetrieved()
    fun listenQuestionsAndReviewsWereRetrieved(callback: suspend () -> Unit)
}
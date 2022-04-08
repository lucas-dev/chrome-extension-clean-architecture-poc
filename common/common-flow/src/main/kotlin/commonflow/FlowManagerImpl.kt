package commonflow


class FlowManagerImpl constructor(private val messagePasser: MessagePasser) : FlowManager {
    override suspend fun requestToFetchItem() {
        messagePasser.sendMessageToActiveWindow(MESSAGE_FROM_POPUP_TO_CONTENTSCRIPT_KEY)
    }

    override fun listenRequestToFetchItem(callback: suspend () -> Unit) {
        messagePasser.listenMessage(MESSAGE_FROM_POPUP_TO_CONTENTSCRIPT_KEY, callback)
    }

    override suspend fun notifyItemWasFetchedAndStored() {
        messagePasser.sendMessage(MESSAGE_FROM_CONTENTSCRIPT_TO_BACKGROUND_KEY)
    }

    override fun listenItemWasFetchedAndStored(callback: suspend () -> Unit) {
        messagePasser.listenMessage(MESSAGE_FROM_CONTENTSCRIPT_TO_BACKGROUND_KEY, callback)
    }

    override suspend fun notifyQuestionsAndReviewsWereRetrieved() {
        messagePasser.sendMessage(MESSAGE_FROM_BACKGROUND_TO_POPUP_KEY)
    }

    override fun listenQuestionsAndReviewsWereRetrieved(callback: suspend () -> Unit) {
        messagePasser.listenMessage(MESSAGE_FROM_BACKGROUND_TO_POPUP_KEY, callback)
    }

    companion object {
        const val MESSAGE_FROM_POPUP_TO_CONTENTSCRIPT_KEY = "FromPopupToCS"
        const val MESSAGE_FROM_CONTENTSCRIPT_TO_BACKGROUND_KEY = "FromCSToBKG"
        const val MESSAGE_FROM_BACKGROUND_TO_POPUP_KEY = "FromBKGToPopup"
    }
}
package commonflow

class ChromeMessagePasser : MessagePasser {
    override suspend fun sendMessage(key: String) {
        sendRequest(key)
    }

    override suspend fun sendMessageToActiveWindow(key: String) {
        sendRequestForActiveWindow(key)
    }

    override fun listenMessage(key: String, callback: suspend () -> Unit) {
        listenResponse(key, callback)
    }
}
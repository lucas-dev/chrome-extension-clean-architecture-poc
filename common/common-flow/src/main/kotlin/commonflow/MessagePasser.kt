package commonflow

interface MessagePasser {
    suspend fun sendMessage(key: String)
    suspend fun sendMessageToActiveWindow(key: String)
    fun listenMessage(key: String, callback: suspend () -> Unit)
}
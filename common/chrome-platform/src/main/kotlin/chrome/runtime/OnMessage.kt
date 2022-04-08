package chrome.runtime

external class OnMessage {
    /** https://developer.chrome.com/docs/extensions/reference/runtime/#event-onMessage */
    fun addListener(listener: (message: dynamic, sender: MessageSender, sendResponse: (response: Any?) -> Unit) -> Boolean)
}
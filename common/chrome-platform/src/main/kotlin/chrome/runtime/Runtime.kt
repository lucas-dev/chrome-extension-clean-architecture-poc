package chrome.runtime

import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/** https://developer.chrome.com/docs/extensions/reference/runtime/ */
external interface Runtime {
    val onInstalled: OnInstalled
    val onMessage: OnMessage
    val lastError: LastError

    @JsName("sendMessage")
    fun sendMessageAsync(
        extensionId: String? = definedExternally,
        message: Any,
        options: Options,
        callback: (response: Any?) -> Unit = definedExternally
    )

    @JsName("getURL")
    fun getUrl(path: String): String
}

suspend fun Runtime.sendMessage(
    extensionId: String? = undefined,
    message: Any,
    options: Options = Options()
): Any? = suspendCoroutine { c ->
    sendMessageAsync(extensionId, message, options) {
        c.resume(it)
    }
}

external class LastError {
    val message: String?
}
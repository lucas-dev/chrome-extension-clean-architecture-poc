package commonflow

import chrome.Chrome
import chrome.runtime.sendMessage
import chrome.tabs.query
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.js.json


suspend inline fun sendRequest(key: String) {
    Chrome.runtime.sendMessage(
        message = json(
            "type" to key
        )
    )
}
suspend inline fun sendRequestForActiveWindow(key: String) {
    val queryInfo = json("active" to true, "currentWindow" to true).unsafeCast<dynamic>()
    Chrome.tabs.query(queryInfo).run {
        Chrome.tabs.sendMessage(this[0].id!!,
            message = json(
                "type" to key
            )
        )
    }
}

inline fun listenResponse(key: String, crossinline callback: suspend () -> Unit) {
    Chrome.runtime.onMessage.addListener { message, sender, sendResponse ->
        if (message.type == key) {
            GlobalScope.launch {
                callback()
            }
        }
        true
    }
}
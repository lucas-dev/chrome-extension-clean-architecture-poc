package chrome.action

import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/** https://developer.chrome.com/docs/extensions/reference/action */
external interface Action {
    val onClicked: OnClicked
    @JsName("setBadgeText")
    fun setBadgeTextAsync(details: dynamic, callback: () -> Unit)
}

suspend fun Action.setBadgeText(text: String? = "", tabId: Number? = undefined) = suspendCoroutine<Unit> { c ->
    val o = js("({})")
    o["text"] = text
    o["tabId"] = tabId
    setBadgeTextAsync(o) { c.resume(Unit) }
}
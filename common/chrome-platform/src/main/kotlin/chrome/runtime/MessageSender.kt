package chrome.runtime

import chrome.tabs.Tab

/** https://developer.chrome.com/docs/extensions/reference/runtime/#type-MessageSender */
external interface MessageSender {
    val frameId: Number?
    val id: String?
    val nativeApplication: String?
    val origin: String?
    val tab: Tab?
    val tlsChannelId: String?
    val url: String?
}
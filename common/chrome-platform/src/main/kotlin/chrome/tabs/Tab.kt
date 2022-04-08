package chrome.tabs

/** https://developer.chrome.com/docs/extensions/reference/tabs/#type-Tab */
external class Tab {
    val active: Boolean
    val audible: Boolean?
    val autoDiscardable: Boolean
    val discarded: Boolean
    val favIconUrl: String?
    val groupId: Number
    val height: Number?
    val highlighted: Boolean
    val id: Number?
    val incognito: Boolean
    val index: Number
    val mutedInfo: MutedInfo
    val openerTabId: Number?
    val pendingUrl: String?
    val pinned: Boolean
    val sessionId: String?

    @JsName("status")
    internal val _status: String
    val title: String?
    val url: String?
    val width: Number?
    val windowId: Number
}
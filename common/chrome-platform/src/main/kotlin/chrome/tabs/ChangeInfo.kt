package chrome.tabs

/** https://developer.chrome.com/docs/extensions/reference/tabs/#onUpdated */
external class ChangeInfo {
    val audible: Boolean?
    val autoDiscardable: Boolean?
    val discarded: Boolean?
    val favIconUrl: String?
    val groupId: Number?
    val mutedInfo: MutedInfo
    val pinned: Boolean?

    @JsName("status")
    internal val _status: String?

    /** https://developer.chrome.com/docs/extensions/reference/tabs/#type-TabStatus */
    val status: TabStatus?
    val title: String?
    val url: String?
}

// Doesn't do anything productive because IR compiler renames this but stops the compiler from complaining the name
// shadows another.
@get:JsName("changeInfoStatusWrapper")
val ChangeInfo.status: TabStatus? get() = if (_status != null) TabStatus.valueOf(_status) else null
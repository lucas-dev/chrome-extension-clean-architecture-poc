package chrome.tabs

external interface UpdateProperties {
    val active: Boolean?
    val autoDiscardable: Boolean?
    val highlighted: Boolean?
    val muted: Boolean?
    val openerTabId: Number?
    val pinned: Boolean?
    val selected: Boolean?
    val url: String?
}

fun UpdateProperties(
    active: Boolean? = undefined,
    autoDiscardable: Boolean? = undefined,
    highlighted: Boolean? = undefined,
    muted: Boolean? = undefined,
    openerTabId: Number? = undefined,
    pinned: Boolean? = undefined,
    selected: Boolean? = undefined,
    url: String? = undefined
): UpdateProperties {
    val o = js("({})")
    o["active"] = active
    o["autoDiscardable"] = autoDiscardable
    o["highlighted"] = highlighted
    o["muted"] = muted
    o["openerTabId"] = openerTabId
    o["pinned"] = pinned
    o["selected"] = selected
    o["url"] = url
    return o.unsafeCast< UpdateProperties>()
}
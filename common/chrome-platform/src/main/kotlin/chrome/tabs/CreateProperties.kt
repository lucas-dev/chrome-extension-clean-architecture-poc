package chrome.tabs

external interface CreateProperties {
    val active: Boolean?
    val index: Number?
    val openerTabId: Number?
    val pinned: Boolean?
    val selected: Boolean?
    val url: String?
    val windowId: Number?
}

fun CreateProperties(
    active: Boolean? = undefined,
    index: Number? = undefined,
    openerTabId: Number? = undefined,
    pinned: Boolean? = undefined,
    selected: Boolean? = undefined,
    url: String? = undefined,
    windowId: Number? = undefined,
): CreateProperties {
    val o = js("({})")
    o["active"] = active
    o["index"] = index
    o["openerTabId"] = openerTabId
    o["pinned"] = pinned
    o["selected"] = selected
    o["url"] = url
    o["windowId"] = windowId
    return o.unsafeCast<CreateProperties>()
}
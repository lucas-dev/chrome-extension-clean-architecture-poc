package chrome.runtime

external interface Options {
    val includeTlsChannelId: Boolean?
}

fun Options(includeTlsChannelId: Boolean? = undefined): Options {
    val o = js("({})")
    o["includeTlsChannelId"] = includeTlsChannelId
    return o.unsafeCast<Options>()
}
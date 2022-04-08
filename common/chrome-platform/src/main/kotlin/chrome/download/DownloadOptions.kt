package chrome.download

/** https://developer.chrome.com/docs/extensions/reference/downloads/#type-DownloadOptions */
external interface DownloadOptions {
    val body: String?
    val _conflictAction: String?
    val filename: String?
    val headers: Array<HeaderNameValuePair>?

    /**
     * "GET" or "POST"
     * https://developer.chrome.com/docs/extensions/reference/downloads/#type-HttpMethod
     */
    val method: String?
    val saveAs: Boolean?
    val url: String
}

fun DownloadOptions(
    body: String? = undefined,
    conflictAction: FilenameConflictAction? = undefined,
    filename: String? = undefined,
    headers: Array<HeaderNameValuePair>? = undefined,
    method: String? = undefined,
    saveAs: Boolean? = undefined,
    url: String
): DownloadOptions {
    val o = js("({})")
    o["body"] = body
    o["conflictAction"] = conflictAction?.name
    o["filename"] = filename
    o["headers"] = headers
    o["method"] = method
    o["saveAs"] = saveAs
    o["url"] = url
    println(JSON.stringify(o))
    return o.unsafeCast<DownloadOptions>()
}

val DownloadOptions.conflictAction: FilenameConflictAction?
    get() = if (_conflictAction == null) {
        null
    } else {
        FilenameConflictAction.valueOf(requireNotNull(_conflictAction))
    }
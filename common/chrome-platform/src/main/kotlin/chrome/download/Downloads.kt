package chrome.download

import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/** https://developer.chrome.com/docs/extensions/reference/downloads */
external class Downloads {
    /** https://developer.chrome.com/docs/extensions/reference/downloads/#method-download */
    @JsName("download")
    fun downloadAsync(options: DownloadOptions, callback: (downloadId: Number) -> Unit)
}

/** https://developer.chrome.com/docs/extensions/reference/downloads/#method-download */
suspend fun Downloads.download(options: DownloadOptions): Number = suspendCoroutine { c ->
    downloadAsync(options) {
        c.resume(it)
    }
}
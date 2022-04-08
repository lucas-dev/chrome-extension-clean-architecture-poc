package chrome

import chrome.action.Action
import chrome.download.Downloads
import chrome.omnibox.Omnibox
import chrome.runtime.Runtime
import chrome.storage.Storage
import chrome.tabs.Tabs
import org.w3c.fetch.RequestInit
import org.w3c.fetch.Response
import kotlin.js.Promise

/**
 * Normally we would use the window object but since background services don't have a window
 * we use this, which is a copy of kotlin's window.fetch method.
 */
external fun fetch(input: dynamic, init: RequestInit = definedExternally): Promise<Response>

external interface ChromePlatform {
    val runtime: Runtime
    val storage: Storage
    val action: Action
    val tabs: Tabs
    val omnibox: Omnibox
    val downloads: Downloads
}

@JsName("chrome")
external object Chrome : ChromePlatform {
    override val runtime: Runtime
    override val storage: Storage
    override val action: Action
    override val tabs: Tabs
    override val omnibox: Omnibox
    override val downloads: Downloads
}
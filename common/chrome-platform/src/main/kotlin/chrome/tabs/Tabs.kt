package chrome.tabs

import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/** https://developer.chrome.com/docs/extensions/reference/tabs */
external interface Tabs {
    val onUpdated: OnUpdated

    @JsName("create")
    fun createAsync(createProperties: CreateProperties, callback: (tab: Tab) -> Unit)

    fun sendMessage(tabId: Number, message: Any?, options: Any? = definedExternally, responseCallback: () -> Unit = definedExternally)

    @JsName("update")
    fun updateAsync(tabId: Number?, updateProperties: UpdateProperties, callback: (tab: Tab) -> Unit)

    /** https://developer.chrome.com/docs/extensions/reference/tabs/#method-discard */
    @JsName("discard")
    fun discardAsync(tabId: Number, callback: (tab: Tab?) -> Unit)

    @JsName("query")
    fun queryAsync(queryInfo: QueryInfo, callback: (result: Array<Tab>) -> Unit)

}

// Doesn't do anything productive because IR compiler renames this but stops the compiler from complaining the name
// shadows another.
@get:JsName("tabStatusWrapper")
val Tab.status: TabStatus get() = TabStatus.valueOf(_status)

/** https://developer.chrome.com/docs/extensions/reference/tabs/#method-create */
suspend fun Tabs.create(createProperties: CreateProperties): Tab = suspendCoroutine { c ->
    createAsync(createProperties) {
        c.resume(it)
    }
}

suspend fun Tabs.update(tabId: Number?, updateProperties: UpdateProperties): Tab = suspendCoroutine { c ->
    updateAsync(tabId, updateProperties) {
        c.resume(it)
    }
}

/** https://developer.chrome.com/docs/extensions/reference/tabs/#method-discard */
suspend fun Tabs.discard(tabId: Number): Tab? = suspendCoroutine { c ->
    discardAsync(tabId) {
        c.resume(it)
    }
}

suspend fun Tabs.query(queryInfo: QueryInfo): Array<Tab> = suspendCoroutine { c ->
    queryAsync(queryInfo) {
        c.resume(it)
    }
}
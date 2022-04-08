package chrome.storage

import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.js.Json
import kotlin.js.json

external interface StorageArea {
    @JsName("get")
    fun getAsync(vararg key: String, callback: (Json) -> Unit)

    @JsName("set")
    fun setAsync(items: Json, callback: () -> Unit)
}

suspend inline fun <reified T> StorageArea.get(key: String): T = suspendCoroutine { c ->
    getAsync(key) {
        c.resume(it[key] as T)
    }
}

suspend fun StorageArea.set(key: String, value: Any?): Unit = suspendCoroutine { c ->
    setAsync(json(key to value)) {
        c.resume(Unit)
    }
}
package storage

import chrome.Chrome
import chrome.storage.get
import chrome.storage.set

suspend inline fun saveToStorage(
    key: String,
    value: String
) {
    Chrome.storage.local.set(key, value)
}

suspend inline fun getFromStorage(
    key: String
): String? {
    return Chrome.storage.local.get<String?>(key).takeUnless { it is Nothing? }
}

suspend inline fun clearStorage(
    key: String
) {
    Chrome.storage.local.set(key, null)
}
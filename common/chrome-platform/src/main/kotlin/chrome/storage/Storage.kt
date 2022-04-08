package chrome.storage

/** https://developer.chrome.com/docs/extensions/reference/storage/ */
external interface Storage {
    val sync: StorageArea
    val local: StorageArea
}
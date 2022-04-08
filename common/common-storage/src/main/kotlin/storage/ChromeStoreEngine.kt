package storage

class ChromeStoreEngine<T> constructor(private val key: String, private val serializer: Serializer<T>) : StoreEngine<T> {
    override suspend fun save(value: T) {
        saveToStorage(key, serializer.objectToString(value))
    }

    override suspend fun get(): T? {
        val storedObject = getFromStorage(key)
        return if (storedObject != null) serializer.stringToObject(storedObject) else null
    }

    override suspend fun clear() {
        clearStorage(key)
    }

}
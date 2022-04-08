package storage

interface StoreEngine<T> {
    suspend fun save(value: T)
    suspend fun get(): T?
    suspend fun clear()
}
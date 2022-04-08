package storage

interface Serializer<T> {
    fun objectToString(obj: T): String
    fun stringToObject(str: String): T
}
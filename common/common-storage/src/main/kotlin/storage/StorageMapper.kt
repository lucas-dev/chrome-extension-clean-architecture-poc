interface StorageMapper<C, E> {

    fun fromStorage(type: C): E

    fun toStorage(type: E): C
}
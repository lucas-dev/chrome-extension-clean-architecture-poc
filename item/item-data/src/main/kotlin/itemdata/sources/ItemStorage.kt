package itemdata.sources

import itemdata.model.ItemEntity

interface ItemStorage {
    suspend fun save(item: ItemEntity)
    suspend fun get(): ItemEntity?
    suspend fun clear()
}
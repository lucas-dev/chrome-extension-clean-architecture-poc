package itemstorage

import itemdata.model.ItemEntity
import itemdata.sources.ItemStorage
import storage.StoreEngine

class ItemStorageImpl constructor(private val storeEngine: StoreEngine<ItemEntity>) :
    ItemStorage {
    override suspend fun save(item: ItemEntity) {
        storeEngine.save(item)
    }

    override suspend fun get(): ItemEntity? {
        return storeEngine.get()
    }

    override suspend fun clear() {
        storeEngine.clear()
    }

    companion object {
        const val ITEM_KEY = "itemId"
    }
}
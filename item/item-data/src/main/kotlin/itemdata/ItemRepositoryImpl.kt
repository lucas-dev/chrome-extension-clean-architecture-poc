package itemdata

import itemdata.sources.ItemHtml
import itemdomain.model.Item
import itemdomain.repository.ItemRepository
import itemdata.sources.ItemStorage

class ItemRepositoryImpl constructor(
    private val storage: ItemStorage,
    private val html: ItemHtml
) : ItemRepository {

    override suspend fun fetchAndStore() {
        storage.clear()
        html.extract()?.let {
            storage.save(it)
        }
    }

    override suspend fun retrieve(): Item? {
        return storage.get()?.let {
            Item(id = it.id)
        }
    }
}
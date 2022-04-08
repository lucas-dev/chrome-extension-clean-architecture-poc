package itemdi

import itemdata.sources.ItemHtml
import itemdata.model.ItemEntity
import itemdata.sources.ItemStorage
import itemdomain.interactors.FetchAndStoreItem
import itemdomain.repository.ItemRepository
import itemstorage.ItemSerializer
import storage.ChromeStoreEngine

interface ItemServiceLocator {
    val serializer: ItemSerializer
    val storeEngine: ChromeStoreEngine<ItemEntity>
    val storage: ItemStorage
    val html: ItemHtml
    val repository: ItemRepository
    val fetchAndStoreItemUseCase: FetchAndStoreItem
}
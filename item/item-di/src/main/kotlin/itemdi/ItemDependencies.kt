package itemdi

import itemdata.ItemRepositoryImpl
import itemdata.sources.ItemHtml
import itemdata.model.ItemEntity
import itemdata.sources.ItemStorage
import itemdomain.interactors.FetchAndStoreItem
import itemdomain.repository.ItemRepository
import itemhtml.ItemHtmlImpl
import itemstorage.ItemSerializer
import itemstorage.ItemStorageImpl
import itemstorage.ItemStorageImpl.Companion.ITEM_KEY
import storage.ChromeStoreEngine

class ItemDependencies : ItemServiceLocator {
    override val serializer: ItemSerializer  by lazy {ItemSerializer()}
    override val storeEngine: ChromeStoreEngine<ItemEntity>  by lazy {ChromeStoreEngine<ItemEntity>(ITEM_KEY, serializer)}
    override val storage: ItemStorage by lazy { ItemStorageImpl(storeEngine) }
    override val html: ItemHtml by lazy { ItemHtmlImpl(document = getDocumentReference()) }
    override val repository: ItemRepository by lazy { ItemRepositoryImpl(storage, html) }
    override val fetchAndStoreItemUseCase: FetchAndStoreItem by lazy { FetchAndStoreItem(repository) }
}
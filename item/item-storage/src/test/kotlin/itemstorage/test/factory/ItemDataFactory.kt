package itemstorage.test.factory

import itemdata.model.ItemEntity

internal object ItemDataFactory {
    fun makeItemEntity() = ItemEntity(id = "234")
    fun makeAnotherItemEntity() = ItemEntity("333")
}
package itemdata.test.factory

import itemdata.model.ItemEntity

internal object ItemDataFactory {
    fun makeItemEntity() = ItemEntity(id = "1986")
}
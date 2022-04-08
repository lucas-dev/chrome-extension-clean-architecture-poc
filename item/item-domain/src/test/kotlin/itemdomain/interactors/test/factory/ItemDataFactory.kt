package itemdomain.interactors.test.factory

import itemdomain.model.Item

internal object ItemDataFactory {
    fun makeItem() = Item(id = "1986")
}
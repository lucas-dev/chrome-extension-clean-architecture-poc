package itemdata.sources

import itemdata.model.ItemEntity

interface ItemHtml {
    fun extract(): ItemEntity?
}
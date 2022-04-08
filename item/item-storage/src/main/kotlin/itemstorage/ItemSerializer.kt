package itemstorage

import itemdata.model.ItemEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import storage.Serializer

class ItemSerializer: Serializer<ItemEntity> {
    override fun objectToString(obj: ItemEntity): String {
        return Json.encodeToString(obj)
    }

    override fun stringToObject(str: String): ItemEntity {
        return Json.decodeFromString<ItemEntity>(str)
    }
}
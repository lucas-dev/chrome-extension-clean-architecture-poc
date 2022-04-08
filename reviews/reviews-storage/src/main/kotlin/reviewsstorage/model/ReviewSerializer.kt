package reviewsstorage.model

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import storage.Serializer

class ReviewSerializer: Serializer<List<ReviewStore>> {
    override fun objectToString(obj: List<ReviewStore>): String {
        return Json.encodeToString(obj)
    }

    override fun stringToObject(str: String): List<ReviewStore> {
        return Json.decodeFromString<List<ReviewStore>>(str)
    }
}
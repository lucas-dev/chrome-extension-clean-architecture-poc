package questionsstorage.model

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import storage.Serializer

class QuestionsSerializer : Serializer<List<QuestionStore>> {
    override fun objectToString(obj: List<QuestionStore>): String {
        return Json.encodeToString(obj)
    }

    override fun stringToObject(str: String): List<QuestionStore> {
        return Json.decodeFromString<List<QuestionStore>>(str)
    }
}
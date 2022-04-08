package questionsstorage.mapper

import StorageMapper
import questionsdata.model.QuestionEntity
import questionsstorage.model.QuestionStore

class QuestionStoreMapper : StorageMapper<QuestionStore, QuestionEntity> {
    override fun fromStorage(type: QuestionStore): QuestionEntity {
        return with(type) {
            QuestionEntity(dateCreated, itemId, sellerId, status, text, id, answer?.run {
                QuestionEntity.AnswerEntity(text, status, dateCreated)
            })
        }
    }

    override fun toStorage(type: QuestionEntity): QuestionStore {
        return with(type) {
            QuestionStore(dateCreated, itemId, sellerId, status, text, id, answer?.run {
                QuestionStore.AnswerStore(text, status, dateCreated)
            })
        }
    }
}
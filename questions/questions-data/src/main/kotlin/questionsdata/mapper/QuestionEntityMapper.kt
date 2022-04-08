package questionsdata.mapper

import commondata.DataMapper
import questionsdata.model.QuestionEntity
import questionsdomain.model.Question

class QuestionEntityMapper: DataMapper<QuestionEntity, Question> {
    override fun fromData(entity: QuestionEntity): Question {
        return with(entity) {
            Question(dateCreated, itemId, sellerId, status, text, id, answer?.run {
                Question.Answer(text, status, dateCreated)
            })
        }
    }

    override fun toData(domain: Question): QuestionEntity {
        return with(domain) {
            QuestionEntity(dateCreated, itemId, sellerId, status, text, id, answer?.run {
                QuestionEntity.AnswerEntity(text, status, dateCreated)
            })
        }
    }
}
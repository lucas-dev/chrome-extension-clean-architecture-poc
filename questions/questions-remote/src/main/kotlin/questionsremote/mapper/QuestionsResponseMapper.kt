package questionsremote.mapper

import commonremote.RemoteMapper
import questionsdata.model.QuestionEntity
import questionsremote.model.QuestionsResponse

class QuestionsResponseMapper : RemoteMapper<QuestionsResponse.QuestionResponse, QuestionEntity> {
    override fun fromRemote(model: QuestionsResponse.QuestionResponse): QuestionEntity {
        return with(model) {
            QuestionEntity(
                dateCreated = dateCreated,
                itemId = itemId,
                sellerId = sellerId,
                status = status,
                text = text,
                id = id,
                answer = answer?.let { answer ->
                    QuestionEntity.AnswerEntity(
                        text = answer.text,
                        status = answer.status,
                        dateCreated = answer.dateCreated
                    )
                }
            )
        }
    }
}
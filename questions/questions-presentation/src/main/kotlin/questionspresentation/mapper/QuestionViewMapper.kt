package questionspresentation.mapper

import commonpresentation.PresentationMapper
import questionsdomain.model.Question
import questionspresentation.model.QuestionView

class QuestionViewMapper : PresentationMapper<QuestionView, Question> {
    override fun toPresentation(type: Question): QuestionView {
        return with(type) {
            QuestionView(text, answer?.run { QuestionView.AnswerView(text) })
        }
    }
}
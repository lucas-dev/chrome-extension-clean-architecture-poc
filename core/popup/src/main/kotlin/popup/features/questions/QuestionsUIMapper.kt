package popup.features.questions

import commonui.UIMapper
import questionspresentation.model.QuestionView
import questionsui.QuestionUI

class QuestionsUIMapper : UIMapper<QuestionUI, QuestionView> {
    override fun mapToUI(type: QuestionView): QuestionUI {
        return with(type) {
            QuestionUI(text, answer?.run {
                QuestionUI.AnswerUI(text)
            })
        }
    }
}
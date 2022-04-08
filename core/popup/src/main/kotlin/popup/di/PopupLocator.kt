package popup.di

import popup.features.common.StateHandler
import popup.mvi.ViewModel
import popup.features.common.UIActions
import questionspresentation.model.QuestionView
import reviewspresentation.model.ReviewView

interface PopupLocator {
    val viewModel: ViewModel
    val questionsUIActions: UIActions<QuestionView>
    val reviewsUIActions: UIActions<ReviewView>
    val stateHandler: StateHandler
}
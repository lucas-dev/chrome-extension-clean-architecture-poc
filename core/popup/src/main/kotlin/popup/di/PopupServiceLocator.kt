package popup.di

import commondi.ServiceLocator
import popup.features.common.StateHandler
import popup.mvi.ViewModel
import popup.features.common.UIActions
import popup.features.questions.QuestionsUIActions
import popup.features.questions.QuestionsUIMapper
import popup.features.reviews.ReviewsUIActions
import popup.features.reviews.ReviewsUIMapper
import questionspresentation.model.QuestionView
import reviewspresentation.model.ReviewView

class PopupServiceLocator constructor(locator: ServiceLocator) :
    PopupLocator {
    override val viewModel: ViewModel by lazy {
        ViewModel(
            locator.flowManager
        )
    }
    override val questionsUIActions: UIActions<QuestionView> by lazy {
        QuestionsUIActions(
            locator.questionsDependencies.views,
            locator.questionsDependencies.viewManipulations,
            locator.questionsDependencies.viewModel,
            QuestionsUIMapper()
        )
    }
    override val reviewsUIActions: UIActions<ReviewView> by lazy {
        ReviewsUIActions(
            locator.reviewsDependencies.views,
            locator.reviewsDependencies.viewManipulations,
            locator.reviewsDependencies.viewModel,
            ReviewsUIMapper()
        )
    }

    override val stateHandler by lazy {
        StateHandler(locator.questionsDependencies.viewModel,
        questionsUIActions, locator.reviewsDependencies.viewModel,
        reviewsUIActions)
    }
}
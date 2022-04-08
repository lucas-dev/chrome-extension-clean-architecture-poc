package popup

import commondi.AppServiceLocator
import commonpresentation.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import popup.di.PopupServiceLocator
import popup.mvi.Events
import popup.mvi.MessagePassingStatus
import popup.mvi.QuestionsAndReviewsStatus
import questionspresentation.model.QuestionView
import reviewspresentation.model.ReviewView

fun main() {
    val locator = AppServiceLocator()
    val locatorPopup = PopupServiceLocator(locator)
    val scope = MainScope()

    setupTabs(locatorPopup)

    requestItem(scope, locatorPopup)

    setupStates(
        scope, locatorPopup,
        locator.questionsDependencies.viewModel,
        locator.reviewsDependencies.viewModel
    )
    setupSearch(scope, locatorPopup)
}

private fun requestItem(scope: CoroutineScope, locatorPopup: PopupServiceLocator) {
    scope.launch {
        locatorPopup.viewModel.dispatch(Events.RequestItem)
    }
}

private fun setupStates(
    scope: CoroutineScope,
    locatorPopup: PopupServiceLocator,
    questionsViewModel: ViewModel<QuestionView>,
    reviewsViewModel: ViewModel<ReviewView>
) {
    scope.launch {
        locatorPopup.viewModel.state.collect { state ->
            when (state.messagePassingStatus) {
                MessagePassingStatus.DataRetrieved -> {
                    locatorPopup.viewModel.dispatch(
                        Events.RequestQuestionsAndReviews
                    )
                }
                MessagePassingStatus.Idle -> print("")
            }
            when (state.questionsAndReviewsStatus) {
                QuestionsAndReviewsStatus.Idle -> {}
                QuestionsAndReviewsStatus.RequestList -> {
                    questionsViewModel.dispatch(commonpresentation.Events.RequestList)
                    reviewsViewModel.dispatch(commonpresentation.Events.RequestList)
                }
                QuestionsAndReviewsStatus.EmitLoading -> {
                    questionsViewModel.emitLoading()
                    reviewsViewModel.emitLoading()
                }
            }
        }
    }
    scope.launch {
        locatorPopup.stateHandler.handleQuestionsState()
    }
    scope.launch {
        locatorPopup.stateHandler.handleReviewsState()
    }
}

private fun setupSearch(scope: CoroutineScope, locatorPopup: PopupServiceLocator) {
    scope.launch {
        locatorPopup.questionsUIActions.setupSearch()
    }
    scope.launch {
        locatorPopup.reviewsUIActions.setupSearch()
    }
}

private fun setupTabs(locatorPopup: PopupServiceLocator) {
    locatorPopup.reviewsUIActions.setTabInactive()
    locatorPopup.questionsUIActions.setupTab {
        locatorPopup.reviewsUIActions.setTabInactive()
    }
    locatorPopup.reviewsUIActions.setupTab {
        locatorPopup.questionsUIActions.setTabInactive()
    }
}
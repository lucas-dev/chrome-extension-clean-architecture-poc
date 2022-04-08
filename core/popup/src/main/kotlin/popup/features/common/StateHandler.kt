package popup.features.common

import commonpresentation.LoadType
import commonpresentation.ViewModel
import commonpresentation.ViewStatus
import questionspresentation.model.QuestionView
import reviewspresentation.model.ReviewView

class StateHandler constructor(
    private val questionsViewModel: ViewModel<QuestionView>,
    private val questionsUIActions: UIActions<QuestionView>,
    private val reviewsViewModel: ViewModel<ReviewView>,
    private val reviewsUIActions: UIActions<ReviewView>
) {
    suspend fun handleQuestionsState() {
        questionsViewModel.state.collect {
            when (val viewStatus = it.viewStatus) {
                is ViewStatus.Error -> questionsUIActions.onError(viewStatus.msg)
                is ViewStatus.Loading -> {
                    when (viewStatus.loadType) {
                        LoadType.FIRST_LOAD -> questionsUIActions.onFirstLoadLoading()
                        LoadType.SEARCH -> {
                            questionsUIActions.onSearchLoading()
                        }
                    }
                }
                is ViewStatus.Loaded -> {
                    if (viewStatus.items.isNotEmpty()) {
                        questionsUIActions.onLoadFinished()
                        when (viewStatus.loadType) {
                            LoadType.FIRST_LOAD -> questionsUIActions.onFirstLoadShowResults(viewStatus.items)
                            LoadType.SEARCH -> questionsUIActions.onSearchShowResults(viewStatus.items)

                        }
                    } else {
                        when (viewStatus.loadType) {
                            LoadType.FIRST_LOAD -> questionsUIActions.onFirstLoadNoResults()
                            LoadType.SEARCH -> questionsUIActions.onSearchNoResults()
                        }
                    }
                }
            }
        }
    }

    suspend fun handleReviewsState() {
        reviewsViewModel.state.collect {
            when (val viewStatus = it.viewStatus) {
                is ViewStatus.Error -> reviewsUIActions.onError(viewStatus.msg)
                is ViewStatus.Loading -> {
                    when (viewStatus.loadType) {
                        LoadType.FIRST_LOAD -> reviewsUIActions.onFirstLoadLoading()
                        LoadType.SEARCH -> {
                            reviewsUIActions.onSearchLoading()
                        }
                    }
                }
                is ViewStatus.Loaded -> {
                    if (viewStatus.items.isNotEmpty()) {
                        reviewsUIActions.onLoadFinished()
                        when (viewStatus.loadType) {
                            LoadType.FIRST_LOAD -> reviewsUIActions.onFirstLoadShowResults(viewStatus.items)
                            LoadType.SEARCH -> reviewsUIActions.onSearchShowResults(viewStatus.items)

                        }
                    } else {
                        when (viewStatus.loadType) {
                            LoadType.FIRST_LOAD -> reviewsUIActions.onFirstLoadNoResults()
                            LoadType.SEARCH -> reviewsUIActions.onSearchNoResults()
                        }
                    }
                }
            }
        }
    }
}
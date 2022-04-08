package popup.features.questions

import commonpresentation.ViewModel
import commonui.UIMapper
import commonui.ViewManipulator
import commonui.Views
import popup.features.common.UIActions
import questionspresentation.model.QuestionView
import questionsui.QuestionUI

class QuestionsUIActions constructor(
    private val views: Views,
    private val viewManipulator: ViewManipulator<QuestionUI>,
    private val viewModel: ViewModel<QuestionView>,
    private val uiMapper: UIMapper<QuestionUI, QuestionView>
) : UIActions<QuestionView> {
    override suspend fun setupSearch() {
        viewManipulator.searchViewBinding.collect { query ->
            viewModel.dispatch(commonpresentation.Events.Search(query))
        }
    }

    override fun setupTab(callback: () -> Unit) {
        views.tab.onclick = {
            viewManipulator.setTabActive()
            viewManipulator.showContainer()
            callback()
            Unit
        }
    }

    override fun setTabInactive() {
        viewManipulator.setTabInactive()
        viewManipulator.hideContainer()
    }

    override fun onError(msg: String) {
        viewManipulator.showError(msg)
    }

    override fun onFirstLoadLoading() {
        viewManipulator.showLoading()
    }

    override fun onSearchLoading() {
        viewManipulator.showLoading()
        viewManipulator.clearList()
    }

    override fun onLoadFinished() {
        viewManipulator.showResults()
    }

    override fun onFirstLoadNoResults() {
        viewManipulator.showEmptyResults()
    }

    override fun onSearchNoResults() {
        viewManipulator.showEmptySearch()
    }

    override fun onFirstLoadShowResults(list: List<QuestionView>) {
        viewManipulator.populateList(
            list.map(
                uiMapper::mapToUI
            )
        )
    }

    override fun onSearchShowResults(list: List<QuestionView>) {
        viewManipulator.populateListHigligtingMatches(
            list.map(
                uiMapper::mapToUI
            )
        )
    }

}
package popup.features.reviews

import commonpresentation.ViewModel
import commonui.UIMapper
import commonui.ViewManipulator
import commonui.Views
import popup.features.common.UIActions
import reviewspresentation.model.ReviewView
import reviewsui.ReviewUI

class ReviewsUIActions constructor(
    private val views: Views,
    private val viewManipulator: ViewManipulator<ReviewUI>,
    private val viewModel: ViewModel<ReviewView>,
    private val uiMapper: UIMapper<ReviewUI, ReviewView>
) : UIActions<ReviewView> {
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

    override fun onFirstLoadShowResults(list: List<ReviewView>) {
        viewManipulator.populateList(
            list.map(
                uiMapper::mapToUI
            )
        )
    }

    override fun onSearchShowResults(list: List<ReviewView>) {
        viewManipulator.populateListHigligtingMatches(
            list.map(
                uiMapper::mapToUI
            )
        )
    }

}
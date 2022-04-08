package commonui

import kotlinx.coroutines.flow.Flow
import org.w3c.dom.HTMLLIElement

interface ViewManipulator<in T> {
    fun setTabActive()
    fun setTabInactive()
    fun showContainer()
    fun hideContainer()
    val searchViewBinding: Flow<String>
    fun getSearchTerm(): String
    fun showLoading()
    fun showEmptySearch()
    fun showEmptyResults()
    fun showResults()
    fun showError(msg: String)
    fun populateList(items: List<T>)
    fun populateListHigligtingMatches(items: List<T>)
    fun clearList()
    fun higlightMatch(text: String, match: String): String
    fun createListElement(item: T): HTMLLIElement
}
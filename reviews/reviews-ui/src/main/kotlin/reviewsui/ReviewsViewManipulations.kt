package reviewsui

import commonui.*
import kotlinx.browser.document
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.dom.clear
import org.w3c.dom.*

class ReviewsViewManipulations constructor(private val views: Views) : ViewManipulator<ReviewUI> {
    override fun setTabActive() {
        views.tab.addClass("active")
    }

    override fun setTabInactive() {
        views.tab.removeClass("active")
    }

    override fun showContainer() {
        views.container.show()
    }

    override fun hideContainer() {
        views.container.hide()
    }

    override val searchViewBinding: Flow<String> = callbackFlow<String> {
        views.search.onkeyup = {
            trySend(views.search.value)
        }
        awaitClose {
            views.search.onkeyup = null
        }
    }

    override fun getSearchTerm(): String {
        return views.search.value
    }

    override fun showLoading() {
        views.loader.show()
        views.list.hide()
        views.emptyData.hide()
        views.emptySearchResults.hide()
        views.error.hide()
    }

    override fun showEmptySearch() {
        views.emptySearchResults.show()
        views.list.hide()
        views.emptyData.hide()
        views.loader.hide()
        views.error.hide()
    }

    override fun showEmptyResults() {
        views.emptyData.show()
        views.emptySearchResults.hide()
        views.list.hide()
        views.loader.hide()
        views.error.hide()
    }

    override fun showError(msg: String) {
        views.error.show()
        views.error.innerHTML = msg
        views.emptyData.hide()
        views.emptySearchResults.hide()
        views.list.hide()
        views.loader.hide()
    }

    override fun populateList(items: List<ReviewUI>) {
        items.forEach { review ->
            val reviewWidget = createListElement(review)
            views.list.appendChild(reviewWidget)
        }
    }

    override fun populateListHigligtingMatches(items: List<ReviewUI>) {
        populateList(items.map { review ->
            review.copy(
                title = higlightMatch(review.title, getSearchTerm()),
                content = higlightMatch(review.content, getSearchTerm()),
            )
        })
    }

    override fun clearList() {
        views.list.clear()
    }

    override fun higlightMatch(text: String, match: String): String {
        return text.replace(match, "<span class='highlight'>$match</span>", ignoreCase = true)
    }

    override fun createListElement(item: ReviewUI): HTMLLIElement {
        val li = document.createElement("li") as HTMLLIElement
        val div = document.createElement("div") as HTMLDivElement
        val h1Title = document.createElement("h1") as HTMLElement
        val pContent = document.createElement("p") as HTMLParagraphElement
        val pRate = document.createElement("p") as HTMLParagraphElement

        h1Title.innerHTML = item.title
        pContent.innerHTML = item.content
        pRate.textContent = item.rate.toString()
        with (div) {
            appendChild(h1Title)
            appendChild(pContent)
            appendChild(pRate)
            li.appendChild(this)
        }
        return li
    }

    override fun showResults() {
        views.list.show()
        views.loader.hide()
        views.emptyData.hide()
        views.emptySearchResults.hide()
    }
}
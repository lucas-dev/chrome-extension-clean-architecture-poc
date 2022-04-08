package questionsui

import commonui.*
import kotlinx.browser.document
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.dom.clear
import org.w3c.dom.HTMLLIElement
import org.w3c.dom.HTMLUListElement

class QuestionsViewManipulations constructor(private val views: Views) : ViewManipulator<QuestionUI> {
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

    override fun showResults() {
        views.list.show()
        views.loader.hide()
        views.emptyData.hide()
        views.emptySearchResults.hide()
        views.error.hide()
    }

    override fun showError(msg: String) {
        views.error.show()
        views.error.innerHTML = msg
        views.list.hide()
        views.loader.hide()
        views.emptyData.hide()
        views.emptySearchResults.hide()
    }

    override fun populateList(items: List<QuestionUI>) {
        items.forEach { question ->
            val questionWidget = createListElement(question)
            views.list.appendChild(questionWidget)
        }
    }

    override fun populateListHigligtingMatches(items: List<QuestionUI>) {
        populateList(items.map { question ->
            question.copy(
                text = higlightMatch(question.text, getSearchTerm()),
                answer = question.answer?.copy(text = higlightMatch(question.answer.text, getSearchTerm()))
            )
        })
    }

    override fun clearList() {
        views.list.clear()
    }

    override fun higlightMatch(text: String, match: String): String {
        return text.replace(match, "<span class='highlight'>$match</span>", ignoreCase = true)
    }

    override fun createListElement(question: QuestionUI): HTMLLIElement {
        val li = document.createElement("li") as HTMLLIElement
        li.innerHTML = question.text
        question.answer?.let { answer ->
            val ulAnswer = document.createElement("ul") as HTMLUListElement
            val liAnswer = document.createElement("li") as HTMLLIElement
            liAnswer.innerHTML = answer.text
            ulAnswer.appendChild(liAnswer)
            li.appendChild(ulAnswer)
        }
        return li
    }
}
package questionsui

import commonui.Views
import kotlinx.browser.document
import org.w3c.dom.*

class QuestionsViews : Views {
    override val tab: HTMLButtonElement
        get() = document.getElementById("tabQuestions") as HTMLButtonElement
    override val container: HTMLDivElement
        get() = document.getElementById("containerQuestions") as HTMLDivElement
    override val search: HTMLInputElement
        get() = document.getElementById("searchViewQuestions") as HTMLInputElement
    override val loader: HTMLDivElement
        get() = document.getElementById("loaderQuestions") as HTMLDivElement
    override val emptySearchResults: HTMLElement
        get() = document.getElementById("noSearchMatchesQuestions") as HTMLElement
    override val emptyData: HTMLElement
        get() = document.getElementById("emptyQuestions") as HTMLElement
    override val error: HTMLElement
        get() = document.getElementById("errorQuestions") as HTMLElement
    override val list: HTMLUListElement
        get() = document.getElementById("listViewQuestions") as HTMLUListElement
}
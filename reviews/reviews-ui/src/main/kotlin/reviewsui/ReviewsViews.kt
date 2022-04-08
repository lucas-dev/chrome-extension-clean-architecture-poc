package reviewsui

import commonui.Views
import kotlinx.browser.document
import org.w3c.dom.*

class ReviewsViews : Views {
    override val tab: HTMLButtonElement
        get() = document.getElementById("tabReviews") as HTMLButtonElement
    override val container: HTMLDivElement
        get() = document.getElementById("containerReviews") as HTMLDivElement
    override val search: HTMLInputElement
        get() = document.getElementById("searchViewReviews") as HTMLInputElement
    override val loader: HTMLDivElement
        get() = document.getElementById("loaderReviews") as HTMLDivElement
    override val emptySearchResults: HTMLElement
        get() = document.getElementById("noSearchMatchesReviews") as HTMLElement
    override val emptyData: HTMLElement
        get() = document.getElementById("emptyReviews") as HTMLElement
    override val error: HTMLElement
        get() = document.getElementById("errorReviews") as HTMLElement
    override val list: HTMLUListElement
        get() = document.getElementById("listViewReviews") as HTMLUListElement
}
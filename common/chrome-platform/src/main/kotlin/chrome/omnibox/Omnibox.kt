package chrome.omnibox

/** https://developer.chrome.com/extensions/omnibox */
external interface Omnibox {
    /** https://developer.chrome.com/extensions/omnibox#method-setDefaultSuggestion */
    fun setDefaultSuggestion(suggestion: DefaultSuggestResult)

    /** https://developer.chrome.com/extensions/omnibox#event-onInputEntered */
    val onInputEntered: OnInputEntered
}

external class OnInputEntered {
    fun addListener(listener: (text: String, suggest: (suggestResults: Array<SuggestResult>) -> Unit) -> Unit)
}
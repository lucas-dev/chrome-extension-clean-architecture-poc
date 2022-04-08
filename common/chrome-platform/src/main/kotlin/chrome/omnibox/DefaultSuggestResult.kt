package chrome.omnibox

/** https://developer.chrome.com/extensions/omnibox#type-SuggestResult */
external interface DefaultSuggestResult {
    val description: String
}

fun DefaultSuggestResult(description: String): DefaultSuggestResult {
    val o = js("{}")
    o.description = description
    return o
}
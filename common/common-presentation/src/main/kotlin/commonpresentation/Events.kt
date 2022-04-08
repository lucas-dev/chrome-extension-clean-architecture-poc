package commonpresentation

sealed class Events {
    data class Search(val query: String): Events()
    object RequestList: Events()
}
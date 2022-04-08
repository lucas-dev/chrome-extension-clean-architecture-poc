package commonpresentation

data class State<T>(
    val viewStatus: ViewStatus<T> = ViewStatus.Idle
)

sealed class ViewStatus<out T> {
    object Idle : ViewStatus<Nothing>()
    data class Loading(val loadType: LoadType) : ViewStatus<Nothing>()
    data class Loaded<T>(val items: List<T>, val loadType: LoadType): ViewStatus<T>()
    data class Error(val msg: String): ViewStatus<Nothing>()
}

enum class LoadType {
    FIRST_LOAD, SEARCH
}
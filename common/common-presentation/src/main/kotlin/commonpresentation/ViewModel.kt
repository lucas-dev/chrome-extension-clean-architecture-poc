package commonpresentation

import kotlinx.coroutines.flow.StateFlow

interface ViewModel<T> {
    val state: StateFlow<State<T>>
    suspend fun dispatch(event: Events)
    suspend fun emitLoading()
}
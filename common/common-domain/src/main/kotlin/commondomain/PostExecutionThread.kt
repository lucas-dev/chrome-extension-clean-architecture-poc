package commondomain

import kotlin.coroutines.CoroutineContext

interface PostExecutionThread {
    val schedulerContext: CoroutineContext
}
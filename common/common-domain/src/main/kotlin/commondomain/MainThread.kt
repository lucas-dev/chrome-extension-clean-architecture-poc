package commondomain

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class MainThread : PostExecutionThread {
    override val schedulerContext: CoroutineContext
        get() = Dispatchers.Default

}
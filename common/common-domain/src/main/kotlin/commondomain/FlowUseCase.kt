package commondomain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in P, R>(private val postExecutionThread: PostExecutionThread) {

    operator fun invoke(parameters: P): Flow<Result<R>> {
        return execute(parameters)
            .catch { e ->
                emit(Result.Error(Exception(e)))
            }.flowOn(postExecutionThread.schedulerContext)
    }

    abstract fun execute(parameters: P): Flow<Result<R>>
}
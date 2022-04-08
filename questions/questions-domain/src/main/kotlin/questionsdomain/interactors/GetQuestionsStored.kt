package questionsdomain.interactors

import commondomain.FlowUseCase
import commondomain.PostExecutionThread
import commondomain.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import questionsdomain.model.Question
import questionsdomain.repository.QuestionsRepository

class GetQuestionsStored constructor(
    private val repository: QuestionsRepository,
    postExecutionThread: PostExecutionThread
) : FlowUseCase<Unit, List<Question>?>(postExecutionThread) {
    override fun execute(parameters: Unit): Flow<Result<List<Question>?>> {
        return flow {
            emit(Result.Loading)
            emit(Result.Success(repository.retrieve()))
        }
    }
}
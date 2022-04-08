package questionsdomain.interactors

import commondomain.FlowUseCase
import commondomain.PostExecutionThread
import commondomain.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import questionsdomain.helpers.StringHelper
import questionsdomain.model.Question
import questionsdomain.repository.QuestionsRepository

class GetQuestionsFiltered constructor(
    private val repository: QuestionsRepository,
    private val stringHelper: StringHelper,
    postExecutionThread: PostExecutionThread
): FlowUseCase<String, List<Question>?>(postExecutionThread) {
    override fun execute(query: String): Flow<Result<List<Question>?>> {
        return flow {
            emit(Result.Loading)
            val questionsFromRepo = repository.retrieve()
            emit(Result.Success(if (query.isNotBlank() && questionsFromRepo != null) {
                questionsFromRepo.filter {
                    stringHelper.findOccurrence(it, query)
                }
            } else {
                repository.retrieve()
            }))
        }
    }

}
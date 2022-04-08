package reviewsdomain.interactors

import commondomain.FlowUseCase
import commondomain.PostExecutionThread
import commondomain.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import reviewsdomain.model.Review
import reviewsdomain.repository.ReviewsRepository

class GetReviewsStored constructor(
    private val repository: ReviewsRepository,
    postExecutionThread: PostExecutionThread
) : FlowUseCase<Unit, List<Review>?>(postExecutionThread) {
    override fun execute(paramters: Unit): Flow<Result<List<Review>?>> {
        return flow {
            emit(Result.Loading)
            emit(
                Result.Success(
                    repository.retrieve()
                )
            )
        }
    }
}
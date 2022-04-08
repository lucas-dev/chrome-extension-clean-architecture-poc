package reviewsdomain.interactors

import commondomain.FlowUseCase
import commondomain.PostExecutionThread
import commondomain.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import reviewsdomain.helpers.StringHelper
import reviewsdomain.model.Review
import reviewsdomain.repository.ReviewsRepository

class GetReviewsFiltered constructor(
    private val repository: ReviewsRepository,
    private val stringHelper: StringHelper,
    postExecutionThread: PostExecutionThread
): FlowUseCase<String, List<Review>?>(postExecutionThread) {
    override fun execute(query: String): Flow<Result<List<Review>?>> {
        return flow {
            emit(Result.Loading)
            val reviews = repository.retrieve()
            emit(Result.Success(
                if (query.isNotBlank() && reviews != null) {
                    reviews.filter {
                        stringHelper.findOccurrence(it, query)
                    }
                } else {
                    repository.retrieve()
                }
            ))

        }
    }

}
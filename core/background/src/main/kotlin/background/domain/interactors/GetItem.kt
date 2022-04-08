package background.domain.interactors

import itemdomain.repository.ItemRepository
import questionsdomain.repository.QuestionsRepository
import reviewsdomain.repository.ReviewsRepository

class GetItem constructor(
    private val itemRepository: ItemRepository,
    private val questionsRepository: QuestionsRepository,
    private val reviewsRepository: ReviewsRepository
) {
    suspend operator fun invoke() {
        questionsRepository.clear()
        reviewsRepository.clear()
        itemRepository.retrieve()?.let {
            questionsRepository.fetchAndStore(it.id)
            reviewsRepository.fetchAndStore(it.id)
        }
    }
}
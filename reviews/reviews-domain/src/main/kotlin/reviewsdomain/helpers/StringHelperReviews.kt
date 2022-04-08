package reviewsdomain.helpers

import reviewsdomain.model.Review

class StringHelperReviews : StringHelper {
    override fun findOccurrence(review: Review, occurrence: String): Boolean {
        return "${review.title} ${review.content}".contains(occurrence, ignoreCase = true)
    }
}
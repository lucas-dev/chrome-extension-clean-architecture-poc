package reviewsdomain.helpers

import reviewsdomain.model.Review

interface StringHelper {
    fun findOccurrence(review: Review, occurrence: String): Boolean
}

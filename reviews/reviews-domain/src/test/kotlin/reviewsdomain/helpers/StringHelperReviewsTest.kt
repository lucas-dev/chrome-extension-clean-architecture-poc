package reviewsdomain.helpers

import reviewsdomain.test.factory.ReviewsDataFactory
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StringHelperReviewsTest {
    private val reviewMocked = ReviewsDataFactory.makeReview()
    @Test
    fun When_QuestionContainsSearchTerm_Expect_OcurrenceFound() {
        val helper = StringHelperReviews()
        assertTrue(helper.findOccurrence(reviewMocked, "works"))
        assertFalse(helper.findOccurrence(reviewMocked, "hola"))
    }
}
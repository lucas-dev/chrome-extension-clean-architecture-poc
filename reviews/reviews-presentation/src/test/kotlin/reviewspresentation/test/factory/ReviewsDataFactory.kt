package reviewspresentation.test.factory

import reviewsdomain.model.Review
import kotlin.js.Date

internal object ReviewsDataFactory {
    fun makeReview() = Review(
        "123",
        Date(2022, 10, 18).toDateString(),
        "a_status",
        "Amazing item",
        "it works perfectly",
        5,
        5,
        15,
        4,
        "reviewerId434",
        Date(2022, 10, 17).toDateString()
    )
}
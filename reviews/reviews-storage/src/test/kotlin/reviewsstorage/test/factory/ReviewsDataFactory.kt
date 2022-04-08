package reviewsstorage.test.factory

import reviewsdata.model.ReviewEntity
import kotlin.js.Date

internal object ReviewsDataFactory {
    fun makeReviewEntity() = ReviewEntity("123",
        Date(2022, 10, 18).toDateString(),
        "a_status",
        "Amazing item",
        "it works perfectly",
        5,
        5,
        15,
        4,
        "reviewerId434",
        Date(2022, 10, 17).toDateString())
}
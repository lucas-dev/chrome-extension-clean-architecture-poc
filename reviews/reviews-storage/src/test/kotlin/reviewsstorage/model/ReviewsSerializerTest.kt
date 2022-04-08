package reviewsstorage.model

import kotlin.js.Date
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class ReviewsSerializerTest {
    private val serializer = ReviewSerializer()

    @Test
    fun testSerializerEncodeQuestionsToString() {
        val reviews = listOf(
            ReviewStore(
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
        )
        assertIs<String>(serializer.objectToString(reviews))
    }

    @Test
    fun testSerializerDecodesStringToQuestions() {
        val reviews = listOf(
            ReviewStore(
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
        )
        assertEquals(serializer.stringToObject(serializer.objectToString(reviews)).first(), reviews.first())
    }
}
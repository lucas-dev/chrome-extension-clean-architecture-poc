package questionsstorage.model

import questionsstorage.test.factory.QuestionsDataFactory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class QuestionsSerializerTest {
    private val serializer = QuestionsSerializer()

    @Test
    fun testSerializerEncodeQuestionsToString() {
        val questions = listOf(QuestionsDataFactory.makeQuestionStore())
        assertIs<String>(serializer.objectToString(questions))
    }

    @Test
    fun testSerializerDecodesStringToQuestions() {
        val questions = listOf(QuestionsDataFactory.makeQuestionStore())
        assertEquals(serializer.stringToObject(serializer.objectToString(questions)).first(), questions.first())
    }
}
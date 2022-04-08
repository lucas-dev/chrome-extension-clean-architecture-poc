package questionsdomain.helpers

import questionsdomain.test.factory.QuestionsDataFactory
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StringHelperQuestionsTest {
    private val questionMocked = QuestionsDataFactory.makeAnotherQuestion()
    @Test
    fun When_QuestionContainsSearchTerm_Expect_OcurrenceFound() {
        val helper = StringHelperQuestions()
        assertTrue(helper.findOccurrence(questionMocked, "THE"))
        assertFalse(helper.findOccurrence(questionMocked, "hola"))
    }
}
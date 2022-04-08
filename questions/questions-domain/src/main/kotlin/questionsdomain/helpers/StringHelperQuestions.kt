package questionsdomain.helpers

import questionsdomain.model.Question

class StringHelperQuestions : StringHelper {
    override fun findOccurrence(question: Question, occurrence: String): Boolean {
        return "${question.text} ${question.answer?.text}".contains(occurrence, ignoreCase = true)
    }

    override fun reduceRepeatedPattern(answer: String, textToCollapse: String): String {
        TODO("Not yet implemented")
    }
}
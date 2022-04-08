package questionsdomain.helpers

import questionsdomain.model.Question

interface StringHelper {
    fun findOccurrence(question: Question, occurrence: String): Boolean
    fun reduceRepeatedPattern(answer: String, textToCollapse: String): String
}

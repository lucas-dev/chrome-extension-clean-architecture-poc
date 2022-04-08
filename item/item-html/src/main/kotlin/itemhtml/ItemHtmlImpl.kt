package itemhtml

import itemdata.sources.ItemHtml
import itemdata.model.ItemEntity
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.asList

class ItemHtmlImpl constructor(private val document: Document?): ItemHtml {
    override fun extract(): ItemEntity? {
        return firstAttempt()?.let { element ->
            firstAttemptContinuation(element)
        } ?: kotlin.run {
            secondAttempt()
        }
    }

    private fun firstAttempt() = document!!.getElementsByClassName("ui-pdp-syi__link")
        .asList()
        .firstOrNull()

    private fun firstAttemptContinuation(firstAttempt: Element): ItemEntity? {
        return firstAttempt
            .getAttribute("href")
            ?.substringAfter("source_item_id=")
            ?.substringBefore("&")
            ?.let {
                ItemEntity(id = it)
            }
    }

    private fun secondAttempt(): ItemEntity? {
        return (document!!.getElementsByName("parent_url")
            .asList().firstOrNull() as? HTMLInputElement)
            ?.value?.substringAfter("p/")?.let {
                ItemEntity(id = it)
            }
    }

}
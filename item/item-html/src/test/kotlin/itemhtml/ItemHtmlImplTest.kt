package itemhtml

import kotlinx.browser.document
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.HTMLBodyElement
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ItemHtmlImplTest {

    @Test
    fun testItemEntityIsCreatedAfterFirstAttemptSucceed() {
        val documentMocked = document.also { document ->
            val htmlFake = (document.createElement("body") as HTMLBodyElement).also { body ->
                body.appendChild((document.createElement("a") as HTMLAnchorElement).also {
                    it.innerHTML = "<a class='ui-pdp-syi__link' href='source_item_id=1986&asdf'></a>"
                })
            }
            document.body = htmlFake
        }
        val itemHtml = ItemHtmlImpl(documentMocked)
        assertNotNull(itemHtml.extract())
        assertEquals("1986", itemHtml.extract()?.id ?: "")
    }

    @Test
    fun testItemEntityIsCreatedAfterSecondAttemptSucceed() {
        val documentMocked = document.also { document ->
            val htmlFake = (document.createElement("body") as HTMLBodyElement).also { body ->
                body.innerHTML = "<input name='parent_url' value='asdf&p/1987'></input>";
            }
            document.body = htmlFake
        }
        val itemHtml = ItemHtmlImpl(documentMocked)
        assertNotNull(itemHtml.extract())
        assertEquals("1987", itemHtml.extract()?.id ?: "")
    }

    @Test
    fun testItemEntityCreationFails() {
        val documentMocked = document.also { document ->
            val htmlFake = (document.createElement("body") as HTMLBodyElement)
            document.body = htmlFake
        }
        val itemHtml = ItemHtmlImpl(documentMocked)
        assertNull(itemHtml.extract())
    }

}
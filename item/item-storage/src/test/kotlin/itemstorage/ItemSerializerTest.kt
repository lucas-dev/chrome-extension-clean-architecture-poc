package itemstorage

import itemdata.model.ItemEntity
import itemstorage.test.factory.ItemDataFactory
import kotlin.test.Test
import kotlin.test.assertIs

class ItemSerializerTest {
    private val serializer = ItemSerializer()

    @Test
    fun testSerializerEncodeItemToString() {
        assertIs<String>(serializer.objectToString(ItemDataFactory.makeItemEntity()))
    }

    @Test
    fun testSerializerDecodesStringToItem() {
        val itemEntityEncodedToString = serializer.objectToString(ItemDataFactory.makeAnotherItemEntity())
        assertIs<ItemEntity>(serializer.stringToObject(itemEntityEncodedToString))
    }
}
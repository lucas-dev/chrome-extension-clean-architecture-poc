package itemdomain.repository

import itemdomain.model.Item

interface ItemRepository {
    suspend fun fetchAndStore()
    suspend fun retrieve(): Item?
}
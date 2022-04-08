package itemdomain.interactors

import itemdomain.repository.ItemRepository

class FetchAndStoreItem constructor(private val repository: ItemRepository) {
    suspend operator fun invoke() {
        repository.fetchAndStore()
    }
}
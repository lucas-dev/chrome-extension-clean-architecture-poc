package contentscript

import commondi.AppServiceLocator

suspend fun main() {
    val locator = AppServiceLocator()
    locator.flowManager.listenRequestToFetchItem {
        locator.itemDependencies.fetchAndStoreItemUseCase()
        locator.flowManager.notifyItemWasFetchedAndStored()
    }
}
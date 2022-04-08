package background

import background.domain.interactors.GetItem
import commondi.AppServiceLocator

suspend fun main() {
    val locator = AppServiceLocator()
    locator.flowManager.listenItemWasFetchedAndStored {
        GetItem(
            locator.itemDependencies.repository,
            locator.questionsDependencies.repository,
            locator.reviewsDependencies.repository
        )()
        locator.flowManager.notifyQuestionsAndReviewsWereRetrieved()
    }

}

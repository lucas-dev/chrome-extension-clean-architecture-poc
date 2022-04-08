package commondi

import commondomain.PostExecutionThread
import commonflow.FlowManager
import commonflow.MessagePasser
import itemdi.ItemServiceLocator
import questionsdi.QuestionsServiceLocator
import reviewsdi.ReviewsServiceLocator

interface ServiceLocator {
    val messagePasser: MessagePasser
    val flowManager: FlowManager
    val postExecutionThread: PostExecutionThread
    val itemDependencies: ItemServiceLocator
    val questionsDependencies: QuestionsServiceLocator
    val reviewsDependencies: ReviewsServiceLocator
}
package commondi

import commondomain.MainThread
import commondomain.PostExecutionThread
import commonflow.ChromeMessagePasser
import commonflow.FlowManager
import commonflow.FlowManagerImpl
import commonflow.MessagePasser
import itemdi.ItemDependencies
import itemdi.ItemServiceLocator
import questionsdi.QuestionDependencies
import questionsdi.QuestionsServiceLocator
import reviewsdi.ReviewsDeps
import reviewsdi.ReviewsServiceLocator

class AppServiceLocator : ServiceLocator {
    override val messagePasser: MessagePasser by lazy { ChromeMessagePasser() }
    override val flowManager: FlowManager by lazy { FlowManagerImpl(messagePasser) }
    override val postExecutionThread: PostExecutionThread by lazy {
        MainThread()
    }
    override val itemDependencies: ItemServiceLocator by lazy { ItemDependencies() }
    override val questionsDependencies: QuestionsServiceLocator by lazy { QuestionDependencies(postExecutionThread) }
    override val reviewsDependencies: ReviewsServiceLocator by lazy { ReviewsDeps(postExecutionThread) }
}
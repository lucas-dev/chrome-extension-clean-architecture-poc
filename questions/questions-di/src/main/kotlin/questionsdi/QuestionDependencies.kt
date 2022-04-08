package questionsdi

import StorageMapper
import commondata.DataMapper
import commondomain.PostExecutionThread
import commonpresentation.PresentationMapper
import commonpresentation.ViewModel
import commonremote.RemoteMapper
import commonui.ViewManipulator
import commonui.Views
import questionsdata.*
import questionsdata.mapper.QuestionEntityMapper
import questionsdata.model.QuestionEntity
import questionsdata.sources.QuestionsRemote
import questionsdata.sources.QuestionsStorage
import questionsdomain.helpers.StringHelper
import questionsdomain.helpers.StringHelperQuestions
import questionsdomain.interactors.GetQuestionsFiltered
import questionsdomain.interactors.GetQuestionsStored
import questionsdomain.model.Question
import questionsdomain.repository.QuestionsRepository
import questionspresentation.model.QuestionView
import questionspresentation.mapper.QuestionViewMapper
import questionspresentation.QuestionsViewModel
import questionsremote.QuestionsRemoteImpl
import questionsremote.model.QuestionsResponse
import questionsremote.mapper.QuestionsResponseMapper
import questionsremote.api.QuestionsApi
import questionsremote.api.QuestionsApiImpl
import questionsstorage.QuestionsStorageImpl
import questionsstorage.model.QuestionStore
import questionsstorage.mapper.QuestionStoreMapper
import questionsstorage.model.QuestionsSerializer
import questionsstorage.QuestionsStorageImpl.Companion.QUESTIONS_KEY
import questionsui.QuestionUI
import questionsui.QuestionsViews
import questionsui.QuestionsViewManipulations
import storage.ChromeStoreEngine
import storage.Serializer
import storage.StoreEngine

class QuestionDependencies constructor(postExecutionThread: PostExecutionThread): QuestionsServiceLocator {
    override val views: Views by lazy { QuestionsViews() }
    override val viewManipulations: ViewManipulator<QuestionUI> by lazy { QuestionsViewManipulations(views) }
    override val serializer: Serializer<List<QuestionStore>> by lazy { QuestionsSerializer() }
    override val storeEngine: StoreEngine<List<QuestionStore>> by lazy { ChromeStoreEngine(QUESTIONS_KEY, serializer) }
    override val storageMapper: StorageMapper<QuestionStore, QuestionEntity> by lazy { QuestionStoreMapper() }
    override val storage: QuestionsStorage by lazy { QuestionsStorageImpl(storeEngine, storageMapper) }
    override val api: QuestionsApi by lazy { QuestionsApiImpl() }
    override val remoteMapper: RemoteMapper<QuestionsResponse.QuestionResponse, QuestionEntity> by lazy { QuestionsResponseMapper() }
    override val remote: QuestionsRemote by lazy { QuestionsRemoteImpl(api, remoteMapper) }
    override val dataMapper: DataMapper<QuestionEntity, Question> by lazy { QuestionEntityMapper() }
    override val repository: QuestionsRepository by lazy { QuestionsRepositoryImpl(storage, remote, dataMapper) }
    override val stringHelper: StringHelper by lazy { StringHelperQuestions() }
    override val getQuestionsStoredUseCase: GetQuestionsStored by lazy { GetQuestionsStored(repository, postExecutionThread) }
    override val getQuestionsFilteredUseCase: GetQuestionsFiltered by lazy {
        GetQuestionsFiltered(
            repository,
            stringHelper,
            postExecutionThread
        )
    }
    override val presentationMapper: PresentationMapper<QuestionView, Question> by lazy { QuestionViewMapper() }
    override val viewModel: ViewModel<QuestionView> by lazy {
        QuestionsViewModel(
            getQuestionsStoredUseCase,
            getQuestionsFilteredUseCase,
            presentationMapper
        )
    }

}
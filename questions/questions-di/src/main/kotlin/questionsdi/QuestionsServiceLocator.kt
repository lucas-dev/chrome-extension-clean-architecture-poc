package questionsdi

import StorageMapper
import commondata.DataMapper
import commonpresentation.PresentationMapper
import commonpresentation.ViewModel
import commonremote.RemoteMapper
import commonui.ViewManipulator
import commonui.Views
import questionsdata.model.QuestionEntity
import questionsdata.sources.QuestionsRemote
import questionsdata.sources.QuestionsStorage
import questionsdomain.helpers.StringHelper
import questionsdomain.interactors.GetQuestionsFiltered
import questionsdomain.interactors.GetQuestionsStored
import questionsdomain.model.Question
import questionsdomain.repository.QuestionsRepository
import questionspresentation.model.QuestionView
import questionsremote.model.QuestionsResponse
import questionsremote.api.QuestionsApi
import questionsstorage.model.QuestionStore
import questionsui.QuestionUI
import storage.Serializer
import storage.StoreEngine

interface QuestionsServiceLocator {
    val views: Views
    val viewManipulations: ViewManipulator<QuestionUI>
    val serializer: Serializer<List<QuestionStore>>
    val storeEngine: StoreEngine<List<QuestionStore>>
    val storageMapper: StorageMapper<QuestionStore, QuestionEntity>
    val storage: QuestionsStorage
    val api: QuestionsApi
    val remoteMapper: RemoteMapper<QuestionsResponse.QuestionResponse, QuestionEntity>
    val remote: QuestionsRemote
    val dataMapper: DataMapper<QuestionEntity, Question>
    val repository: QuestionsRepository
    val stringHelper: StringHelper
    val getQuestionsStoredUseCase: GetQuestionsStored
    val getQuestionsFilteredUseCase: GetQuestionsFiltered
    val presentationMapper: PresentationMapper<QuestionView, Question>
    val viewModel: ViewModel<QuestionView>
}
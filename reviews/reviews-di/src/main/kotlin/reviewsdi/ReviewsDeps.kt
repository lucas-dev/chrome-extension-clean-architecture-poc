package reviewsdi

import StorageMapper
import commondata.DataMapper
import commondomain.PostExecutionThread
import commonpresentation.PresentationMapper
import commonpresentation.ViewModel
import commonremote.RemoteMapper
import commonui.ViewManipulator
import commonui.Views
import reviewsdata.*
import reviewsdata.mapper.ReviewEntityMapper
import reviewsdata.model.ReviewEntity
import reviewsdata.sources.ReviewsRemote
import reviewsdata.sources.ReviewsStorage
import reviewsdomain.helpers.StringHelper
import reviewsdomain.helpers.StringHelperReviews
import reviewsdomain.interactors.GetReviewsFiltered
import reviewsdomain.interactors.GetReviewsStored
import reviewsdomain.model.Review
import reviewsdomain.repository.ReviewsRepository
import reviewspresentation.model.ReviewView
import reviewspresentation.mapper.ReviewViewMapper
import reviewspresentation.ReviewsViewModel
import reviewsremote.ReviewsRemoteImpl
import reviewsremote.model.ReviewsResponse
import reviewsremote.mapper.ReviewsResponseMapper
import reviewsremote.api.ReviewsApi
import reviewsremote.api.ReviewsApiImpl
import reviewsstorage.model.ReviewSerializer
import reviewsstorage.ReviewsStorageImpl
import reviewsstorage.model.ReviewStore
import reviewsstorage.mapper.ReviewStoreMapper
import reviewsstorage.ReviewsStorageImpl.Companion.REVIEWS_KEY
import reviewsui.ReviewUI
import reviewsui.ReviewsViews
import reviewsui.ReviewsViewManipulations
import storage.ChromeStoreEngine
import storage.Serializer
import storage.StoreEngine

class ReviewsDeps constructor(postExecutionThread: PostExecutionThread) : ReviewsServiceLocator {
    override val views: Views by lazy { ReviewsViews() }
    override val viewManipulations: ViewManipulator<ReviewUI> by lazy { ReviewsViewManipulations(views) }
    override val serializer: Serializer<List<ReviewStore>> by lazy { ReviewSerializer() }
    override val storeEngine: StoreEngine<List<ReviewStore>> by lazy { ChromeStoreEngine(REVIEWS_KEY, serializer) }
    override val storageMapper: StorageMapper<ReviewStore, ReviewEntity> by lazy { ReviewStoreMapper() }
    override val storage: ReviewsStorage by lazy { ReviewsStorageImpl(storeEngine, storageMapper) }
    override val api: ReviewsApi by lazy { ReviewsApiImpl() }
    override val remoteMapper: RemoteMapper<ReviewsResponse.ReviewResponse, ReviewEntity> by lazy { ReviewsResponseMapper() }
    override val remote: ReviewsRemote by lazy { ReviewsRemoteImpl(api, remoteMapper) }
    override val dataMapper: DataMapper<ReviewEntity, Review> by lazy { ReviewEntityMapper() }
    override val repository: ReviewsRepository by lazy { ReviewsRepositoryImpl(storage, remote, dataMapper) }
    override val stringHelper: StringHelper by lazy { StringHelperReviews() }
    override val getReviewsStoredUseCase: GetReviewsStored by lazy { GetReviewsStored(repository, postExecutionThread) }
    override val getReviewsFilteredUseCase: GetReviewsFiltered by lazy {
        GetReviewsFiltered(
            repository,
            stringHelper,
            postExecutionThread
        )
    }
    override val presentationMapper: PresentationMapper<ReviewView, Review> by lazy { ReviewViewMapper() }
    override val viewModel: ViewModel<ReviewView> by lazy {
        ReviewsViewModel(
            getReviewsStoredUseCase,
            getReviewsFilteredUseCase,
            presentationMapper
        )
    }
}
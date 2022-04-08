package reviewsdi

import StorageMapper
import commondata.DataMapper
import commonpresentation.PresentationMapper
import commonpresentation.ViewModel
import commonremote.RemoteMapper
import commonui.ViewManipulator
import commonui.Views
import reviewsdata.model.ReviewEntity
import reviewsdata.sources.ReviewsRemote
import reviewsdata.sources.ReviewsStorage
import reviewsdomain.helpers.StringHelper
import reviewsdomain.interactors.GetReviewsFiltered
import reviewsdomain.interactors.GetReviewsStored
import reviewsdomain.model.Review
import reviewsdomain.repository.ReviewsRepository
import reviewspresentation.model.ReviewView
import reviewsremote.model.ReviewsResponse
import reviewsremote.api.ReviewsApi
import reviewsstorage.model.ReviewStore
import reviewsui.ReviewUI
import storage.Serializer
import storage.StoreEngine

interface ReviewsServiceLocator {
    val views: Views
    val viewManipulations: ViewManipulator<ReviewUI>
    val serializer: Serializer<List<ReviewStore>>
    val storeEngine: StoreEngine<List<ReviewStore>>
    val storageMapper: StorageMapper<ReviewStore, ReviewEntity>
    val storage: ReviewsStorage
    val api: ReviewsApi
    val remoteMapper: RemoteMapper<ReviewsResponse.ReviewResponse, ReviewEntity>
    val remote: ReviewsRemote
    val dataMapper: DataMapper<ReviewEntity, Review>
    val repository: ReviewsRepository
    val stringHelper: StringHelper
    val getReviewsStoredUseCase: GetReviewsStored
    val getReviewsFilteredUseCase: GetReviewsFiltered
    val presentationMapper: PresentationMapper<ReviewView, Review>
    val viewModel: ViewModel<ReviewView>
}
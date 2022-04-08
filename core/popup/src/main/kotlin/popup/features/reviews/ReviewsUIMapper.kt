package popup.features.reviews

import commonui.UIMapper
import reviewspresentation.model.ReviewView
import reviewsui.ReviewUI

class ReviewsUIMapper : UIMapper<ReviewUI, ReviewView> {
    override fun mapToUI(type: ReviewView): ReviewUI {
        return with(type) {
            ReviewUI(title, content, dateCreated, rate, likes, dislikes)
        }
    }
}
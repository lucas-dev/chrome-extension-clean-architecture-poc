package reviewspresentation.mapper

import commonpresentation.PresentationMapper
import reviewsdomain.model.Review
import reviewspresentation.model.ReviewView

class ReviewViewMapper : PresentationMapper<ReviewView, Review> {
    override fun toPresentation(type: Review): ReviewView {
        return with(type) {
            ReviewView(title, content, dateCreated, rate, likes, dislikes)
        }
    }
}
package commonpresentation

interface PresentationMapper<out Presentation, in Domain> {
    fun toPresentation(type: Domain): Presentation
}
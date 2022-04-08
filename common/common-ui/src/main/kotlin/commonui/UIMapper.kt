package commonui

interface UIMapper<out UI, in Presentation> {
    fun mapToUI(type: Presentation): UI
}
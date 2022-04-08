package chrome.tabs

/** https://developer.chrome.com/docs/extensions/reference/tabs/#onUpdated */
external class OnUpdated {
    fun addListener(listener: (tabId: Int, changeInfo: ChangeInfo, tab: Tab) -> Unit)
}
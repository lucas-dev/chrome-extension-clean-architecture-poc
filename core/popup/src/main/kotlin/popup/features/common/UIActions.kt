package popup.features.common

interface UIActions<in T> {
    suspend fun setupSearch()
    fun setupTab(callback: () -> Unit)
    fun setTabInactive()
    fun onError(msg: String)
    fun onFirstLoadLoading()
    fun onSearchLoading()
    fun onLoadFinished()
    fun onFirstLoadNoResults()
    fun onSearchNoResults()
    fun onFirstLoadShowResults(list: List<T>)
    fun onSearchShowResults(list: List<T>)
}
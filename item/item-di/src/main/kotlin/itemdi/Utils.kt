package itemdi

//  TODO workaround to catch ReferenceError exception when instantiating ItemDependencies() in background script
//  We don't have access to kotlinx.browser.document in that component and this need to be fixed when migrating to a proper
//  dependencies handling mechanism
fun getDocumentReference() = try {
    kotlinx.browser.document
} catch (e: Throwable) {
    null
}
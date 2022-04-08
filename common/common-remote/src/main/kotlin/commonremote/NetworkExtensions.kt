package commonremote

import kotlinx.coroutines.await

suspend fun String.fetch() = chrome.fetch(this).await().json().await().asDynamic()
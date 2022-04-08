package chrome.tabs

external interface QueryInfo {
    var status: String? get() = definedExternally; set(value) = definedExternally
    var lastFocusedWindow: Boolean? get() = definedExternally; set(value) = definedExternally
    var windowId: Number? get() = definedExternally; set(value) = definedExternally
    var windowType: String? get() = definedExternally; set(value) = definedExternally
    var active: Boolean? get() = definedExternally; set(value) = definedExternally
    var index: Number? get() = definedExternally; set(value) = definedExternally
    var title: String? get() = definedExternally; set(value) = definedExternally
    var url: dynamic /* String | Array<String> */ get() = definedExternally; set(value) = definedExternally
    var currentWindow: Boolean? get() = definedExternally; set(value) = definedExternally
    var highlighted: Boolean? get() = definedExternally; set(value) = definedExternally
    var pinned: Boolean? get() = definedExternally; set(value) = definedExternally
    var audible: Boolean? get() = definedExternally; set(value) = definedExternally
    var muted: Boolean? get() = definedExternally; set(value) = definedExternally
}
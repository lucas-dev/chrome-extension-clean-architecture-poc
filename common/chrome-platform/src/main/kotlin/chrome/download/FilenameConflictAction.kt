package chrome.download

/** https://developer.chrome.com/docs/extensions/reference/downloads/#type-FilenameConflictAction */
enum class FilenameConflictAction {
    uniquify,
    overwrite,
    prompt
}
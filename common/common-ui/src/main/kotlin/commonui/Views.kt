package commonui

import org.w3c.dom.*

interface Views {
    val tab: HTMLButtonElement
    val container: HTMLDivElement
    val search: HTMLInputElement
    val loader: HTMLDivElement
    val emptySearchResults: HTMLElement
    val emptyData: HTMLElement
    val error: HTMLElement
    val list: HTMLUListElement
}
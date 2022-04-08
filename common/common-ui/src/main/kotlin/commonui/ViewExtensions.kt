package commonui

import org.w3c.dom.HTMLElement

fun HTMLElement.hide(){
    this.style.display = "none"
}

fun HTMLElement.show() {
    this.style.display = "block"
}

fun HTMLElement.addClass(className: String) {
    if (this.className.contains(className).not())
        this.className += className
}

fun HTMLElement.removeClass(className: String) {
    this.className = this.className.replace("active", "")
}
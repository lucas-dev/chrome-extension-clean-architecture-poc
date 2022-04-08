plugins {
    kotlinJS
}

kotlin {
    js(IR) {
        browser {
            binaries.executable()
        }
    }
}

dependencies {
    with(projects) {
        implementAll(
            item.itemDomain,
            item.itemData,
            item.itemStorage,
            item.itemHtml
        )
    }
}
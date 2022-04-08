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
            item.itemDi,
            questions.questionsDi,
            reviews.reviewsDi,
            common.commonFlow
        )
    }
}
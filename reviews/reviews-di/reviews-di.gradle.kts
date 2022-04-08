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
    with(projects.reviews) {
        implementAll(reviewsDomain, reviewsData,
            reviewsStorage, reviewsRemote, reviewsPresentation, reviewsUi)
    }
}
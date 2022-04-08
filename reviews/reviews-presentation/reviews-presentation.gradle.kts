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
    implementAll(projects.common.commonPresentation, projects.reviews.reviewsDomain)
    testImplementation(Deps.test)
}
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
    implementProjects()
    implementAll(Deps.coroutines, Deps.test)
}

fun DependencyHandlerScope.implementProjects() {
    implementAll(projects.common.commonRemote, projects.reviews.reviewsData)
}
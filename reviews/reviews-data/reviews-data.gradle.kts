plugins {
    kotlinJS
    serialization
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
    implementDeps()
}

fun DependencyHandlerScope.implementProjects() {
    with(projects) {
        implementAll(common.commonData, reviews.reviewsDomain, common.commonTest)
    }
}

fun DependencyHandlerScope.implementDeps() {
    implementation(Deps.coroutines)
    testImplementation(Deps.test)
}
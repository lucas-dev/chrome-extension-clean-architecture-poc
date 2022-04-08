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
    implementAll(projects.item.itemDomain, projects.common.commonTest)
}

fun DependencyHandlerScope.implementDeps() {
    implementAll(Deps.coroutines, Deps.serialization)
    testImplementation(Deps.test)
}
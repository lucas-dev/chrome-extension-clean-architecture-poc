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
    implementAll(projects.common.commonStorage, projects.questions.questionsData)
}

fun DependencyHandlerScope.implementDeps() {
    implementAll(Deps.serialization, Deps.coroutines, Deps.test)
}
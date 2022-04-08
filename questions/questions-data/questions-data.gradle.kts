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
        implementAll(common.commonData, common.commonTest, questions.questionsDomain)
    }
}

fun DependencyHandlerScope.implementDeps() {
    implementation(Deps.coroutines)
    testImplementation(Deps.test)
}
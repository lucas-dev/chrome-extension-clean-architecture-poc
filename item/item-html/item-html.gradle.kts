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
    implementDeps()
}

fun DependencyHandlerScope.implementProjects() {
    implementAll(projects.item.itemData, projects.common.commonTest)
}

fun DependencyHandlerScope.implementDeps() {
    implementation(Deps.coroutines)
    testImplementation(Deps.test)
}
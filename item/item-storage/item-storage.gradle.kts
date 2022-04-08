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
    with(projects) {
        implementAll(common.commonStorage, common.commonTest, item.itemData)
    }
}

fun DependencyHandlerScope.implementDeps() {
    implementation(Deps.coroutines)
    testImplementation(Deps.test)
}
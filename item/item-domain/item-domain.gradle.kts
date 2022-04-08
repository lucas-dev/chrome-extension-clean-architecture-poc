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
    implementation(projects.common.commonTest)
    implementation(Deps.coroutines)
    testImplementation(Deps.test)
}
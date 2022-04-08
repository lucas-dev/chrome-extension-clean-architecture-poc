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
    implementAll(projects.common.commonDomain, projects.common.commonTest)
    testImplementation(Deps.test)
}
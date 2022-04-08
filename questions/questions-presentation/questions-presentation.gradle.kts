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
    with (projects) {
        implementAll(common.commonPresentation,
            common.commonTest,
            questions.questionsDomain)
    }
    testImplementation(Deps.test)
}
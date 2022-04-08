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
    with(projects.questions) {
        implementAll(
            questionsDomain,
            questionsData,
            questionsStorage,
            questionsRemote,
            questionsUi,
            questionsPresentation
        )
    }
}
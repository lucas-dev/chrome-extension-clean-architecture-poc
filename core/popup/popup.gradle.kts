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
    implementQuestionsDeps()
    implementReviewsDeps()
    implementation(projects.common.commonDi)
    implementation(Deps.coroutines)
}

val content: Configuration by configurations.creating

artifacts {
    add("content", file("$buildDir/distributions/${name}.js")) {
        builtBy("browserProductionWebpack")
    }
    add("content", file("$buildDir/distributions/popup.html"))
    add("content", file("$buildDir/distributions/popup.css"))
}

fun DependencyHandlerScope.implementQuestionsDeps() {
    with(projects.questions) {
        implementAll(questionsUi, questionsPresentation)
    }
}

fun DependencyHandlerScope.implementReviewsDeps() {
    with(projects.reviews) {
        implementAll(reviewsUi, reviewsPresentation)
    }
}
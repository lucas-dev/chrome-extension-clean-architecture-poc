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
    implementation(projects.common.commonDi)
    implementation(Deps.coroutines)
    implementation(Deps.html)
}

val content: Configuration by configurations.creating

artifacts {
    add("content", file("$buildDir/distributions/${name}.js")) {
        builtBy("browserProductionWebpack")
    }
}
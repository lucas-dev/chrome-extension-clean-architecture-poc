object Config {
    object Version {
        const val projectVersion = "1.0"
        const val projectGroup = "melifeedback"
        const val projectName = "MercadoFeedback"
        const val projectDescription = "Buscá por palabras clave entre las reviews y las preguntas y respuestas"
        const val kotlinVersion: String = "1.6.10"
    }

    object SourcePaths {
        const val manifest = "manifest.json"
        const val res = "resources"
        const val root = "/"
    }
}

object Deps {
    object Version {
        const val coroutines = "1.6.0"
        const val html = "0.7.2"
        const val serialization = "1.0.0"
    }
    const val test = "org.jetbrains.kotlin:kotlin-test-js"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${Version.coroutines}"
    const val html = "org.jetbrains.kotlinx:kotlinx-html-js:${Version.html}"
    const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Version.serialization}"
}

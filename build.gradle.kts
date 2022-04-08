import Config.SourcePaths.manifest
import Config.SourcePaths.res
import Config.SourcePaths.root
import Config.Version.projectDescription
import Config.Version.projectGroup
import Config.Version.projectName
import Config.Version.projectVersion
import org.jetbrains.kotlin.gradle.plugin.KotlinJsCompilerType

allprojects {
    group = projectGroup
    version = projectVersion

    repositories {
        applyDefault()
    }
}

plugins {
    distribution
    kotlinJSMainDep
}

val content: Configuration by configurations.creating

dependencies {
    applyCoreDependencies(content, projects.core.background, projects.core.contentScript, projects.core.popup)
}

distributions {
    main {
        contents {
            from(manifest) {
                expand(
                    "name" to projectName,
                    "version" to projectVersion,
                    "description" to projectDescription
                )
            }

            from(content)

            from(file(res))

            into(root)
        }
    }
}

tasks.setupTasks()


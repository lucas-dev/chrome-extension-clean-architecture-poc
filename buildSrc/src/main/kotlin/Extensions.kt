import Config.Version.kotlinVersion
import Config.Version.projectDescription
import Config.Version.projectName
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.file.CopySpec
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec
import Config.Version.projectVersion
import org.gradle.api.artifacts.Configuration
import org.gradle.api.internal.catalog.DelegatingProjectDependency
import org.gradle.api.tasks.TaskContainer
import org.gradle.kotlin.dsl.*

val PluginDependenciesSpec.kotlin: PluginDependencySpec
    get() = id("kotlin")

val PluginDependenciesSpec.kotlinJSMainDep: PluginDependencySpec
    get() = kotlinJS.also {
        it.version(kotlinVersion) apply(false)
    }

val PluginDependenciesSpec.kotlinJS: PluginDependencySpec
    get() = kotlin("js")

val PluginDependenciesSpec.serialization: PluginDependencySpec
    get() = kotlin("plugin.serialization") version("1.4.0")

fun RepositoryHandler.applyDefault() {
    mavenCentral()
    jcenter()
    maven("https://jitpack.io")
}

fun DependencyHandlerScope.applyCoreDependencies(content: Configuration, vararg dependencies: DelegatingProjectDependency) {
    dependencies.forEach {
        content(it) {
            targetConfiguration = "content"
        }
    }
}

fun TaskContainer.setupTasks() {
    named("installDist").configure {
        dependsOn(named("assemble"))
    }

    named("distTar").configure {
        enabled = false
    }
}

fun DependencyHandler.implementAll(vararg projects: DelegatingProjectDependency) {
    projects.forEach {
        add("implementation", it)
    }
}

fun DependencyHandler.implementAll(vararg libs: String) {
    libs.forEach {
        add("implementation", it)
    }
}
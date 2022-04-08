enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "mercadofeedback"

include(
    "core:background",
    "core:content-script",
    "core:popup",
    "common:chrome-platform",
    "common:common-storage",
    "common:common-remote",
    "common:common-data",
    "common:common-di",
    "common:common-flow",
    "common:common-ui",
    "common:common-presentation",
    "common:common-domain",
    "common:common-test",
    "item:item-data",
    "item:item-domain",
    "item:item-html",
    "item:item-storage",
    "item:item-di",
    "questions:questions-data",
    "questions:questions-domain",
    "questions:questions-remote",
    "questions:questions-storage",
    "questions:questions-ui",
    "questions:questions-presentation",
    "questions:questions-di",
    "reviews:reviews-data",
    "reviews:reviews-domain",
    "reviews:reviews-remote",
    "reviews:reviews-storage",
    "reviews:reviews-ui",
    "reviews:reviews-presentation",
    "reviews:reviews-di"
)

fun setBuildFileName(projects: Collection<ProjectDescriptor>) {
    for (project in projects) {
        project.apply { buildFileName = "$name.gradle.kts" }
        setBuildFileName(project.children)
    }
}

setBuildFileName(rootProject.children)

val localSettings = file("local.settings.gradle.kts")
if (localSettings.exists()) {
    apply(from = localSettings)
}
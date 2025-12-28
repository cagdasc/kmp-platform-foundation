pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
}

rootProject.name = "kmp-foundation"
include(":coroutines")
include(":coroutines-di")
include(":core")
include(":core-di")

includeBuild(".") {
    dependencySubstitution {
        substitute(module("com.github.cagdasc:coroutines"))
            .using(project(":coroutines"))

        substitute(module("com.github.cagdasc:core"))
            .using(project(":core"))
    }
}

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    // Kotlin/Wasm adds Node.js and Binaryen distribution repositories for its tool setup.
    // Allow those project repositories while still listing the shared dependency repos here.
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ahmed-nawaz-portfolio"
include(":composeApp")

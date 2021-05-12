import ext.api
import ext.configureArtifact

plugins {
    id("java-library")
    id("kotlin")
}

configureArtifact()

dependencies {
    // external
    api(Deps.Rx.all)
}

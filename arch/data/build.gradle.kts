import ext.api
import ext.configureArtifact

plugins {
    id("com.android.library")
    id("kotlin-android")
}

configureArtifact()

dependencies {
    // external
    api(Deps.Rx.all)
    api(Deps.Retrofit.all)
}

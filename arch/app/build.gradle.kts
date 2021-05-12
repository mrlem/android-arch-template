import ext.api
import ext.configureArtifact

plugins {
    id("com.android.library")
    id("kotlin-android")
}

configureArtifact()

android {
    defaultConfig {
        versionCode = 1
        versionName = "1.0"

        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    // external
    api(Deps.Android.constraintlayout)
    api(Deps.Android.viewbinding)
    api(Deps.Android.coreKtx)
    api(Deps.Android.activityKtx)
    api(Deps.Android.fragmentKtx)
    api(Deps.Android.liveDataKtx)
    api(Deps.Koin.all)
    api(Deps.Rx.all)
}

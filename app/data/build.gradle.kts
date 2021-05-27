plugins {
    id("com.android.library")
    id("kotlin-android")
}

dependencies {
    implementation(project(":arch:data"))

    // internal
    implementation(project(":app:domain"))
}

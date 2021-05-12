plugins {
    id("com.android.library")
    id("kotlin-android")
}

dependencies {
    // internal
    implementation(project(":arch:android-arch-data"))
    implementation(project(":sample:domain"))

    // external
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
}

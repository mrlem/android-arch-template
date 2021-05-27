plugins {
    id("com.android.library")
    id("kotlin-android")
}

dependencies {
    implementation(project(":arch:data"))

    // internal
    implementation(project(":app:domain"))

    // external
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
}

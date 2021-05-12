plugins {
    id("java-library")
    id("kotlin")
}

dependencies {
    // internal
    implementation(project(":arch:android-arch-domain"))

    // external
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
}

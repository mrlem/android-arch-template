plugins {
    id("java-library")
    id("kotlin")
}

dependencies {
    implementation(project(":arch:domain"))

    // external
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
}

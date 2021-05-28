plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    defaultConfig {
        applicationId = "org.example.myapp"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(":arch:ui"))

    // internal
    implementation(project(":app:domain"))
    implementation(project(":app:data"))

    // external
    testImplementation("junit:junit:4.13")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}

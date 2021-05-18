import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin as AndroidApplicationPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin as AndroidLibraryPlugin
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.2.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath("io.insert-koin:koin-gradle-plugin:${Versions.koin}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    // base kotlin compile config
    tasks.withType<KotlinCompile<KotlinJvmOptions>> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }

    // base config for android modules
    fun BaseExtension.configureAndroid() {
        setCompileSdkVersion(30)
        buildToolsVersion("30.0.2")

        defaultConfig {
            minSdkVersion(21)
            targetSdkVersion(30)
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildTypes {
            getByName("release") {
                isMinifyEnabled = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        sourceSets {
            getByName("main") {
                java.setSrcDirs(
                    listOf(
                        Sources.Main.KOTLIN,
                        Sources.Main.RESOURCES
                    )
                )
            }
            getByName("test") {
                java.setSrcDirs(
                    listOf(
                        Sources.Test.KOTLIN,
                        Sources.Test.RESOURCES
                    )
                )
            }
        }
    }

    // base config for android application modules
    plugins.withType<AndroidApplicationPlugin> {
        androidApplication {
            configureAndroid()

            buildTypes {
                getByName("debug") {
                    applicationIdSuffix = ".debug"
                }

                getByName("release") {
                    isShrinkResources = true
                }
            }
        }
    }

    // base config for android library modules
    plugins.withType<AndroidLibraryPlugin> {
        androidLibrary {
            configureAndroid()
        }
    }
}

// tasks
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

// generic extensions
fun Project.androidApplication(configure: AppExtension.() -> Unit) =
    extensions.configure(AppExtension::class, configure)

fun Project.androidLibrary(configure: LibraryExtension.() -> Unit) =
    extensions.configure(LibraryExtension::class, configure)

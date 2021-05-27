import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin as AndroidApplicationPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin as AndroidLibraryPlugin
import arch.Sources
import arch.Versions
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        // FIXME - figure out how to make imports work for this
        classpath("com.android.tools.build:gradle:${arch.Versions.androidGradlePlugin}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${arch.Versions.kotlin}")
        classpath("io.insert-koin:koin-gradle-plugin:${arch.Versions.koin}")
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
            jvmTarget = arch.Versions.javaVersion
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

// extensions
fun BaseExtension.configureAndroid() {
    setCompileSdkVersion(arch.Versions.compileSdkVersion)
    buildToolsVersion(arch.Versions.buildToolsVersion)

    defaultConfig {
        minSdkVersion(arch.Versions.minSdkVersion)
        targetSdkVersion(arch.Versions.targetSdkVersion)
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
                    arch.Sources.Main.KOTLIN,
                    arch.Sources.Main.RESOURCES
                )
            )
        }
        getByName("test") {
            java.setSrcDirs(
                listOf(
                    arch.Sources.Test.KOTLIN,
                    arch.Sources.Test.RESOURCES
                )
            )
        }
    }
}

// generic extensions
fun Project.androidApplication(configure: AppExtension.() -> Unit) =
    extensions.configure(AppExtension::class, configure)

fun Project.androidLibrary(configure: LibraryExtension.() -> Unit) =
    extensions.configure(LibraryExtension::class, configure)

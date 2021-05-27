import arch.Versions
import arch.ext.archProjects
import arch.ext.archTasks
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

subprojects {
    tasks.withType<KotlinCompile<KotlinJvmOptions>> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = Versions.javaVersion
        }
    }

    archProjects()
}

tasks {
    archTasks()
}

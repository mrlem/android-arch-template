package ext

import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import java.net.URI

fun PublishingExtension.configure(
    projectName: String,
    projectDescription: String,
    projectUrl: String,
    licenseName: String,
    licenseUrl: String,
    developerId: String,
    developerName: String,
    developerEmail: String,
    scmConnectionUrl: String,
    scmUrl: String,
    sonatypeUsername: String,
    sonatypePassword: String
) {
    repositories {
        maven {
            name = "OSSRHStaging"
            url = URI.create("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = sonatypeUsername
                password = sonatypePassword
            }
        }
        maven {
            name = "OSSRHSnapshots"
            url = URI.create("https://oss.sonatype.org/content/repositories/snapshots/")
            credentials {
                username = sonatypeUsername
                password = sonatypePassword
            }
        }
    }

    publications.withType(MavenPublication::class.java).configureEach {
        pom {
            name.set(projectName)
            description.set(projectDescription)
            url.set(projectUrl)

            licenses {
                license {
                    name.set(licenseName)
                    url.set(licenseUrl)
                }
            }

            developers {
                developer {
                    id.set(developerId)
                    name.set(developerName)
                    email.set(developerEmail)
                }
            }

            scm {
                connection.set(scmConnectionUrl)
                developerConnection.set(scmConnectionUrl)
                url.set(scmUrl)
            }
        }
    }
}

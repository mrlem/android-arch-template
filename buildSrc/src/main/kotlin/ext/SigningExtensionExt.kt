package ext

import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.plugins.signing.SigningExtension

fun SigningExtension.configure(publishing: PublishingExtension) {
    publishing.publications.withType(MavenPublication::class.java).forEach {
        sign(it)
    }
}

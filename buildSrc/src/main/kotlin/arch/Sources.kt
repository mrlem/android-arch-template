package arch

object Sources {

    private const val SOURCES = "src"

    object Main {

        private const val MAIN = "$SOURCES/main"

        const val KOTLIN = "$MAIN/kotlin"
        const val RESOURCES = "$MAIN/resources"

    }

    object Test {

        private const val TEST = "$SOURCES/test"

        const val KOTLIN = "$TEST/kotlin"
        const val RESOURCES = "$TEST/resources"

    }

}

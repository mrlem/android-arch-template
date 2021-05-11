rootProject.name = "android-arch"

// list all projects
include(
    ":arch:app",
    ":arch:domain",
    ":arch:data",
    ":sample:app",
    ":sample:domain",
    ":sample:data"
)

// set project names to generate correct artifact ids
project(":arch:app").name = "android-arch-app"
project(":arch:domain").name = "android-arch-domain"
project(":arch:data").name = "android-arch-data"

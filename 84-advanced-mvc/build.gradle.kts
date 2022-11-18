plugins {
    application
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.yaml:snakeyaml:1.33")
}

application {
    mainClass.set("it.unibo.mvc.DrawNumberApp")
}

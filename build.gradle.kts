plugins {
    id("java")
}

group = "me.djtheredstoner"
version = "1.0-SNAPSHOT"

java {
    toolchain.languageVersion = JavaLanguageVersion.of(25)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(files("lib/com.microsoft.z3.jar"))
}

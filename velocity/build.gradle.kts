plugins {
    id("java")
    id("com.github.johnrengelman.shadow")
}

dependencies {
    implementation(project(":common"))
    compileOnly("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
    annotationProcessor("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
}

tasks.shadowJar {
    archiveBaseName.set("tgsync-velocity")
    archiveClassifier.set("")
}

tasks.build {
    dependsOn(tasks.shadowJar)
}
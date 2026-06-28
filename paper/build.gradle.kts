plugins {
    id("java")
    id("com.github.johnrengelman.shadow")
}

dependencies {
    implementation(project(":common"))
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    compileOnly(files("libs/TotemGuardAPI-2.1.3.jar"))
}

tasks.shadowJar {
    archiveBaseName.set("tgsync-paper")
    archiveClassifier.set("")
}

tasks.build {
    dependsOn(tasks.shadowJar)
}
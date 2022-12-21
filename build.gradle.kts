import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    // orchestrator for UI tests
    androidTestUtil "androidx.test:orchestrator:$ORCHESTRATOR"

    // runner
    androidTestImplementation "androidx.test:runner:$RUNNER"

    // mock web server for UI tests
    androidTestImplementation "com.squareup.okhttp3:mockwebserver:$MOCK_WEB_SERVER_VERSION"

    // junit5
    testImplementation "org.junit.jupiter:junit-jupiter:$JUNIT_VERSION"
    androidTestImplementation "org.junit.jupiter:junit-jupiter-api:$JUNIT_VERSION"
    androidTestImplementation "de.mannodermaus.junit5:android-test-core:$JUNIT_PLUGIN"
    androidTestImplementation "de.mannodermaus.junit5:android-test-runner:$JUNIT_PLUGIN"

    // espresso
    androidTestImplementation "androidx.test.espresso:espresso-core:$ESPRESSO_VERSION"
    androidTestImplementation("androidx.test.espresso:espresso-contrib:$ESPRESSO_VERSION") {
        exclude group: "org.checkerframework", module: "checker"
    }
    androidTestImplementation "androidx.test.espresso:espresso-intents:$ESPRESSO_VERSION"
    androidTestImplementation("androidx.test.espresso:espresso-accessibility:$ESPRESSO_VERSION") {
        exclude group: "org.checkerframework", module: "checker"
    }
    androidTestImplementation "androidx.test.espresso.idling:idling-concurrent:$ESPRESSO_VERSION"
    androidTestImplementation "androidx.test.espresso:espresso-idling-resource:$ESPRESSO_VERSION"
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}
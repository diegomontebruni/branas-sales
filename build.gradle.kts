import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.10"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

group = "com.montebruni"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

val mockkVersion = "1.13.4"
val kotlinLoggingVersion = "3.0.5"
val springMockkVersion = "3.1.2"
val swaggerVersion = "3.0.0"
val swaggerUIVersion = "3.0.0"

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("io.github.microutils:kotlin-logging-jvm:$kotlinLoggingVersion")
	implementation("io.springfox:springfox-swagger2:$swaggerVersion")
	implementation("io.springfox:springfox-swagger-ui:$swaggerUIVersion")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude("org.mockito")
	}
	testImplementation("io.mockk:mockk:${mockkVersion}")
	testImplementation("com.ninja-squad:springmockk:${springMockkVersion}")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
